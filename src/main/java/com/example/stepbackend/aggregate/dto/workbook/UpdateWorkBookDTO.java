package com.example.stepbackend.aggregate.dto.workbook;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateWorkBookDTO {
    private String workBookName;
    private String description;
    private Long workBookNo;
}
