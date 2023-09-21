package com.example.stepbackend.aggregate.dto.board;

import com.example.stepbackend.aggregate.entity.Board;
import com.example.stepbackend.aggregate.entity.Heart;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class ReadBoardPageDTO {
    private Long boardNo;

    private String[] questionNos;

    private String[] questionTypes;

    private String questionName;

    private LocalDate createdAt;

    private String description;

    private Long heartCount;

    private Boolean isClicked;

    public static Page<ReadBoardPageDTO> toEntity(Page<Board> boards, Page<Heart> hearts) {
        Map<Long, Boolean> isClickedMap = hearts.stream()
                .collect(Collectors.toMap(Heart::getBoardNo, Heart::getIsClicked));

        return boards.map(board -> {
            Boolean isClicked = isClickedMap.get(board.getBoardNo());

            return ReadBoardPageDTO.builder()
                    .boardNo(board.getBoardNo())
                    .questionNos(board.getQuestionNos().split(", "))
                    .questionTypes(board.getQuestionTypes().split(", "))
                    .questionName(board.getBoardName())
                    .createdAt(board.getCreatedAt())
                    .description(board.getDescription())
                    .heartCount(board.getHeartCount())
                    .isClicked(isClicked)
                    .build();
        });
    }
}
