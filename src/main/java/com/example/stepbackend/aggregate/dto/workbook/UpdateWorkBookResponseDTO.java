package com.example.stepbackend.aggregate.dto.workbook;

import com.example.stepbackend.aggregate.entity.WorkBook;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateWorkBookResponseDTO {
    private String workBookName;
    private String description;

    public static UpdateWorkBookResponseDTO toEntity(WorkBook workBook) {
        return UpdateWorkBookResponseDTO.builder()
                .workBookName(workBook.getWorkBookName())
                .description(workBook.getDescription())
                .build();
    }
}
