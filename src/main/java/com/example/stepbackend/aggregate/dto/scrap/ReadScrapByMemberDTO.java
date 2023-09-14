package com.example.stepbackend.aggregate.dto.scrap;

import com.example.stepbackend.aggregate.entity.Question;
import com.example.stepbackend.aggregate.entity.QuestionByMember;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ReadScrapByMemberDTO {

    private Integer markedNo;

    private Boolean correctedMarkingStatus;

    public static Page<ReadScrapByMemberDTO> fromEntity(Page<QuestionByMember> questionByMembers) {
        List<ReadScrapByMemberDTO> dtoList = questionByMembers.getContent().stream().map(ReadScrapByMemberDTO::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(dtoList, questionByMembers.getPageable(), questionByMembers.getTotalElements());
    }

    public static ReadScrapByMemberDTO fromEntity(QuestionByMember questionByMember) {
        return ReadScrapByMemberDTO.builder()
                .correctedMarkingStatus(questionByMember.getCorrectedMarkingStatus())
                .markedNo(questionByMember.getMarkedNo())
                .build();
    }
}
