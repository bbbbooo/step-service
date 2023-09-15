package com.example.stepbackend.aggregate.dto.workbook;

import com.example.stepbackend.aggregate.entity.WorkBook;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ReadWorkBookDTO {
    private Long workBookNo;

    private String[] questionNos;

    private Boolean isShared;

    private String[] questionTypes;

    private String questionName;

    private String lastUpdatedTime;

    public static Page<ReadWorkBookDTO> fromEntity(Page<WorkBook> workBooks) {
        List<ReadWorkBookDTO> readWorkBookDTOS = workBooks.getContent().stream().map(ReadWorkBookDTO::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(readWorkBookDTOS, workBooks.getPageable(), workBooks.getTotalElements());
    }

    public static ReadWorkBookDTO fromEntity(WorkBook workBook){
        String formattedLastUpdatedTime = null;
        if (workBook.getLastUpdatedTime() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            formattedLastUpdatedTime = workBook.getLastUpdatedTime().format(formatter);
        }

        String[] questionTypes = workBook.getQuestionTypes().split(", "); // 여러 타입을 분리

        return ReadWorkBookDTO.builder()
                .workBookNo(workBook.getWorkBookNo())
                .questionNos(workBook.getQuestionNos().split(", "))
                .isShared(workBook.getIsShared())
                .questionTypes(questionTypes)
                .questionName(workBook.getWorkBookName())
                .lastUpdatedTime(formattedLastUpdatedTime)
                .build();
    }
}
