package com.example.stepbackend.aggregate.dto.workbook;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateWorkBookNameDTO {
    private String workBookName;
    private Long workBookNo;
}
