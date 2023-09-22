package com.example.stepbackend.aggregate.dto.board;

import com.example.stepbackend.aggregate.entity.WorkBook;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateSolveAfterResponseDTO {
    private Long workbookNo;

    private String message;

    public static CreateSolveAfterResponseDTO fromEntity(WorkBook workBook) {
        return CreateSolveAfterResponseDTO.builder()
                .workbookNo(workBook.getWorkBookNo())
                .message("성공적으로 문제집으로 옮겨졌습니다.")
                .build();
    }
}
