package com.example.stepbackend.aggregate.dto.board;

import com.example.stepbackend.aggregate.entity.Scrap;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReadScrapBoardDTO {

    private Long scrapNo;

    public static ReadScrapBoardDTO fromEntity(Scrap scrap) {
        return ReadScrapBoardDTO.builder()
                .scrapNo(scrap.getScrapNo())
                .build();
    }
}
