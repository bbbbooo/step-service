package com.example.stepbackend.aggregate.dto.workbook;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class IsSharedWorkBookDTO {
    private Long workBookNo;

    private Long questionNo;

    private Long memberNo;
}
