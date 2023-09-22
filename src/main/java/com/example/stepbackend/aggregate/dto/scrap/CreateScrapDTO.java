package com.example.stepbackend.aggregate.dto.scrap;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;

@Data
@Builder
public class CreateScrapDTO {

    private Long questionNo;

    private Integer markedNo;

    private Boolean correctedMarkingStatus;
}
