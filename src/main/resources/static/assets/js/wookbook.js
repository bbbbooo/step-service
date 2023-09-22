window.jsPDF = window.jspdf.jsPDF;
window.canvas = window.html2canvas;

function generateWorkBook() {

const jsonData = /*[[${res}]]*/ null;
const customFontFamily = '/assets/font/KoPubWorld Batang Medium.ttf';
const pdf = new jsPDF('p', 'mm', 'a4');

// 한글 폰트를 추가합니다.
pdf.addFileToVFS('/assets/font/KoPubWorld Batang Medium.ttf');
pdf.addFont('/assets/font/KoPubWorld Batang Medium.ttf', customFontFamily, 'normal');
pdf.setFont(customFontFamily);

// 문제 출력 설정
let x = 10; // 시작 X 좌표
let y = 10; // 시작 Y 좌표
const boxHeight = 100; // 사각형 상자 높이
const lineHeight = 10; // 행 간격
let correctAnswer = [];

jsonData.forEach((question, index) => {
const questionSubject = question.questionSubject;
const options = [
question.view1,
question.view2,
question.view3,
question.view4,
question.view5
];

correctAnswer.push(question.questionCorrectAnswer);

// 문제 설명 출력
pdf.text(`${index + 1}. ${questionSubject}`, 10, y);
y += 5;

// 사각형 상자 안에 문제 내용 출력
const textWidth = 190;
const splittedText = pdf.splitTextToSize(question.questionBody, textWidth);
// const textHeight = (splittedText.length * 7) + 5; // 각 줄에 대한 높이 계산 (7은 줄 간격, 5는 여백)
const textHeight = (splittedText.length * 6.9); // 각 줄에 대한 높이 계산 (7은 줄 간격, 5는 여백)

pdf.rect(10, y, textWidth, textHeight - 5);
pdf.text(splittedText, 10, y + 7);
y += textHeight + 5;

// 한 페이지 넘어가면 보기 다음 페이지에서 이어나가게 출력
if (y >= pdf.internal.pageSize.height) {
pdf.addPage();
y = 20;
}

// 보기 출력
options.forEach((option, optionIndex) => {
const optionLabel = `①②③④⑤`.charAt(optionIndex);
pdf.text(`${optionLabel}\t ${option}`, 10, y);
pdf.text(option, x + 10, y);
y += 8;

if (y >= pdf.internal.pageSize.height) {
pdf.addPage();
y = 8;
}
});

// 다음 문제와의 간격 조정
y += 10;

// 다음 문제를 위해 Y 좌표 조정
// y += boxHeight + 10;

// 페이지 분리 처리
if (index < jsonData.length - 1 && y + boxHeight + 10 > pdf.internal.pageSize.height) {
pdf.addPage();
y = 10;
}
});

// 정답
pdf.addPage();
pdf.text(`정답 : ${correctAnswer}`, x, 10);

// PDF 저장
pdf.save('question_paper.pdf');
}
