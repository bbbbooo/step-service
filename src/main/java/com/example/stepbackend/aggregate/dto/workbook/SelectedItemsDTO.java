package com.example.stepbackend.aggregate.dto.workbook;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SelectedItemsDTO {
    private List<Long> selectedItems;
}
