package com.example.stepbackend.aggregate.dto.scrap;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ReadScrapAndMemberDTO {
    private ReadScrapDTO readScrapDTO;
    private ReadScrapByMemberDTO readScrapByMemberDTO;

    public static List<ReadScrapAndMemberDTO> combineLists(List<ReadScrapDTO> readScrapDTOS, List<ReadScrapByMemberDTO> readScrapByMemberDTOS){
        List<ReadScrapAndMemberDTO> combinedList = new ArrayList<>();

        for (int i = 0; i < readScrapDTOS.size(); i++) {
            ReadScrapAndMemberDTO combinedDTO = ReadScrapAndMemberDTO.builder()
                    .readScrapDTO(readScrapDTOS.get(i))
                    .readScrapByMemberDTO(readScrapByMemberDTOS.get(i))
                    .build();

            combinedList.add(combinedDTO);
        }

        return combinedList;
    }

    public static ReadScrapAndMemberDTO combine(ReadScrapDTO readScrapDTO, ReadScrapByMemberDTO readScrapByMemberDTO) {
        return ReadScrapAndMemberDTO.builder()
                .readScrapDTO(readScrapDTO)
                .readScrapByMemberDTO(readScrapByMemberDTO)
                .build();
    }
}
