window.jsPDF = window.jspdf.jsPDF;
window.canvas = window.html2canvas;
function generatePDF() {

    document.getElementById('question-content');
    const pdf = new jsPDF('p', 'mm', 'a4');

    html2canvas(document.getElementById('question-content'), {scale: 2}) // scale 값을 조절 하여 화질 향상
        .then(function (canvas) {
        const imgData = canvas.toDataURL('image/png', 1.0);
        pdf.addImage(imgData, 'PNG', 0, 0, 210, 297); // A4 사이즈에 맞게 조절

        // PDF를 저장할 때 사용할 파일 이름 및 형식 설정
        pdf.save('question.pdf');
    })
        .catch(function (error) {
        console.error('Error rendering HTML to canvas:', error);
    });
}
