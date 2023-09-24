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

    private Boolean hadShared;

    public static FindWorkBookResponse fromEntity(WorkBook workBook) {
        return FindWorkBookResponse.builder()
                .hadShared(workBook.getHadShared())
                .build();
    }
}
