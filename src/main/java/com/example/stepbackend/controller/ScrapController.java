package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.scrap.CreateScrapDTO;
import com.example.stepbackend.aggregate.dto.scrap.ReadScrapDTO;
import com.example.stepbackend.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;

    @GetMapping("")
    public String get(){

        return "testPage";
    }

    @GetMapping("/myPage/myScrap")
    public String getMyAllScrap(@PageableDefault Pageable pageable, Model model){
        Long memberNo = 1L;

        Page<ReadScrapDTO> readScrapDTOPage = scrapService.findAllScrap(memberNo, pageable);

        model.addAttribute("allScrap", readScrapDTOPage);

        return "scrap/myScrapPage";
    }

//    @PostMapping("/{questionNo}/scrap")
//    public String create(@PathVariable Long questionNo, @RequestBody CreateScrapDTO createScrapDTO){
//        Long memberNo = 1L;
//
//        scrapService.createScrap(createScrapDTO, questionNo, memberNo);
//
//        return "scrap/myScrapPage";
//    }
}
