package com.example.stepbackend.aggregate.dto.scrap;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CancelScrapResponseDTO {
    private List<Long> scrapNos;
    private String message;

    public static CancelScrapResponseDTO toDTO(CancelScrapRequestDTO cancelScrapRequestDTO) {
        return CancelScrapResponseDTO.builder()
                .scrapNos(cancelScrapRequestDTO.getScrapNos())
                .message("성공적으로 삭제되었습니다.")
                .build();
    }
}
