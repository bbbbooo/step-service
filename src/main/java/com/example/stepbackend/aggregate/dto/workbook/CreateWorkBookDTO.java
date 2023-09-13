package com.example.stepbackend.aggregate.dto.workbook;

import com.example.stepbackend.aggregate.entity.WorkBook;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CreateWorkBookDTO {
    private Long workBookNo;

    private String questionNos;

    private Long memberNo;

    public static CreateWorkBookDTO fromEntity(WorkBook workBook) {
        return CreateWorkBookDTO.builder()
                .workBookNo(workBook.getWorkBookNo())
                .questionNos(workBook.getQuestionNos())
                .memberNo(workBook.getMemberNo())
                .build();
    }
}
