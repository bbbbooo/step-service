package com.example.stepbackend.aggregate.dto.workbook;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class DeleteWorkBookResponseDTO {
    private List<Long> workBookNos;

    public static DeleteWorkBookResponseDTO toRequest(DeleteWorkBookRequestDTO deleteWorkBookRequestDTO) {
        return DeleteWorkBookResponseDTO.builder()
                .workBookNos(deleteWorkBookRequestDTO.getWorkBookNos())
                .build();
    }
}
