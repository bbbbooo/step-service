package com.example.stepbackend.aggregate.dto.workbook;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DeleteWorkBookRequestDTO {
    private List<Long> workBookNos;
}
