package com.example.stepbackend.aggregate.dto.workbook;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DeleteWorkBookRequestDTO {
    private List<Long> workBookNos;
}
