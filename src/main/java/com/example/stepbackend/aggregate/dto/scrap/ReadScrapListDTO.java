package com.example.stepbackend.aggregate.dto.scrap;

import com.example.stepbackend.aggregate.entity.Scrap;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReadScrapListDTO {
    private Long scrapNo;

    private Integer markedNo;

    private Boolean correctedMarkingStatus;

    public static Page<ReadScrapListDTO> fromEntity(Page<Scrap> scraps) {
        List<ReadScrapListDTO> readScrapListDTOS = scraps.getContent().stream().map(ReadScrapListDTO::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(readScrapListDTOS, scraps.getPageable(), scraps.getTotalElements());
    }

    public static ReadScrapListDTO fromEntity(Scrap scrap){
        return ReadScrapListDTO.builder()
                .scrapNo(scrap.getScrapNo())
                .markedNo(scrap.getMarkedNo())
                .correctedMarkingStatus(scrap.getCorrectedMarkingStatus())
                .build();
    }
}
