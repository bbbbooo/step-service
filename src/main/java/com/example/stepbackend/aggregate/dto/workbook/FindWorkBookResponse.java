package com.example.stepbackend.aggregate.dto.workbook;

import com.example.stepbackend.aggregate.entity.WorkBook;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindWorkBookResponse {

    private Long workBookNo;

    public static FindWorkBookResponse fromEntity(WorkBook workBook) {
        return FindWorkBookResponse.builder()
                .workBookNo(workBook.getWorkBookNo())
                .build();
    }
}
