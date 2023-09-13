package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.workbook.CreateWorkBookDTO;
import com.example.stepbackend.aggregate.dto.workbook.SelectedItemsDTO;
import com.example.stepbackend.service.WorkbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/workbook")
public class WorkBookController {

    private final WorkbookService workbookService;

    @PostMapping("create")
    @ResponseBody
    public ResponseEntity<CreateWorkBookDTO> create(@RequestBody SelectedItemsDTO selectedItemsDTO){
        Long memberNo = 1L;
        List<Integer> selectedItems = selectedItemsDTO.getSelectedItems();

        CreateWorkBookDTO createWorkBookDTO = workbookService.createWorkbook(memberNo, selectedItems);

        return ResponseEntity.ok(createWorkBookDTO);
    }
}
