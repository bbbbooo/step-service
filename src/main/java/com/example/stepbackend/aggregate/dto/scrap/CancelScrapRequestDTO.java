package com.example.stepbackend.aggregate.dto.scrap;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CancelScrapRequestDTO {
    private List<Long> scrapNos;
}
