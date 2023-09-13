package com.example.stepbackend.aggregate.dto.workbook;

import com.example.stepbackend.aggregate.entity.WorkBook;
import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ReadWorkBookDetailDTO {
    private List<Integer> questionNos;

    private String workBookName;

    public static ReadWorkBookDetailDTO fromEntity(WorkBook workBook) {
        List<Integer> questionNos = Arrays.stream(workBook.getQuestionNos().split(", "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        return ReadWorkBookDetailDTO.builder()
                .workBookName(workBook.getWorkBookName())
                .questionNos(questionNos)
                .build();
    }
}
