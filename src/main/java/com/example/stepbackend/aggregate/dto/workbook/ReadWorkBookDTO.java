package com.example.stepbackend.aggregate.dto.workbook;

import com.example.stepbackend.aggregate.entity.WorkBook;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ReadWorkBookDTO {
    private Long workBookNo;

    private String questionNos;

    private Boolean isShared;


    public static Page<ReadWorkBookDTO> fromEntity(Page<WorkBook> workBooks) {
        List<ReadWorkBookDTO> readWorkBookDTOS = workBooks.getContent().stream().map(ReadWorkBookDTO::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(readWorkBookDTOS, workBooks.getPageable(), workBooks.getTotalElements());
    }

    public static ReadWorkBookDTO fromEntity(WorkBook workBook){
        return ReadWorkBookDTO.builder()
                .workBookNo(workBook.getWorkBookNo())
                .questionNos(workBook.getQuestionNos())
                .isShared(workBook.getIsShared())
                .build();
    }
}
