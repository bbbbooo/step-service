package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.scrap.CreateScrapDTO;
import com.example.stepbackend.aggregate.dto.scrap.ReadScrapAndMemberDTO;
import com.example.stepbackend.aggregate.dto.scrap.ReadScrapByMemberDTO;
import com.example.stepbackend.aggregate.dto.scrap.ReadScrapDTO;
import com.example.stepbackend.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/scrap")
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;

    @GetMapping("/myPage/myScrap")
    public String getMyAllScrap(@PageableDefault Pageable pageable, Model model){
        Long memberNo = 1L;

        Page<ReadScrapDTO> readScrapDTOPage = scrapService.findAllScrap(memberNo, pageable);
        Page<ReadScrapByMemberDTO> readScrapByMemberDTOS = scrapService.findAllScrapByMember(memberNo, pageable);


        model.addAttribute("scraps", ReadScrapAndMemberDTO.combineLists(readScrapDTOPage.getContent(), readScrapByMemberDTOS.getContent()));
        model.addAttribute("allScrap", readScrapDTOPage);

        return "scrap/myScrapPage";
    }

    @GetMapping("detail/{questionNo}")
    public String getScrap(@PathVariable Long questionNo,@RequestParam Integer markedNo, Model model){
        Long memberNo = 1L;

        ReadScrapDTO readScrapDTO = scrapService.findScrap(memberNo, questionNo, markedNo);
        ReadScrapByMemberDTO readScrapByMemberDTO = scrapService.findScrapByMember(memberNo, questionNo);

        model.addAttribute("scrap", ReadScrapAndMemberDTO.combine(readScrapDTO, readScrapByMemberDTO));

        return "scrap/scrapDetail";
    }

    @PostMapping("/create")
    @ResponseBody
    public String create( @RequestBody CreateScrapDTO createScrapDTO){
        Long memberNo = 1L;

        scrapService.createScrap(createScrapDTO, memberNo);

        return "scrap/myScrapPage";
    }
}
