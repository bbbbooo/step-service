package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.board.ReadScrapBoardDTO;
import com.example.stepbackend.aggregate.dto.scrap.*;
import com.example.stepbackend.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/scrap")
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;

    /* 마이 페이지 내 스크랩 조회 */
    @GetMapping("/myPage/myScrap")
    public String getMyAllScrap(@PageableDefault Pageable pageable, Model model){
        Long memberNo = 1L;

        Page<ReadScrapDTO> readScrapDTOPage = scrapService.findAllScrap(memberNo, pageable);
        Page<ReadScrapByMemberDTO> readScrapByMemberDTOS = scrapService.findAllScrapByMember(memberNo, pageable);
        Page<ReadScrapListDTO> readScrapListDTOS = scrapService.findScrapNo(memberNo, pageable);

        model.addAttribute("scraps", ReadScrapAndMemberDTO.combineLists(readScrapDTOPage.getContent(), readScrapByMemberDTOS.getContent()));
        model.addAttribute("allScrap", readScrapDTOPage);
        model.addAttribute("scrapNos", readScrapListDTOS);

        return "scrap/myScrapPage";
    }

    /* 마이 페이지 내 스크랩 상세 조회*/
    @GetMapping("detail/{questionNo}")
    public String getScrap(@PathVariable Long questionNo,@RequestParam Integer markedNo, Model model){
        Long memberNo = 1L;

        ReadScrapDTO readScrapDTO = scrapService.findScrap(memberNo, questionNo, markedNo);
        ReadScrapByMemberDTO readScrapByMemberDTO = scrapService.findScrapByMember(memberNo, questionNo, markedNo);

        model.addAttribute("scrap", ReadScrapAndMemberDTO.combine(readScrapDTO, readScrapByMemberDTO));

        return "scrap/scrapDetail";
    }

    /* 문제 풀고 스크랩할때 스크랩 했는지 안했는지 조사 */
    @GetMapping("/workbook/detail/{questionNo}")
    @ResponseBody
    public ResponseEntity<ReadScrapBoardDTO> getScrapBoard(@PathVariable Long questionNo, @RequestParam Integer markedNo){
        Long memberNo = 1L;

        ReadScrapBoardDTO readScrapBoardDTO = scrapService.findScrapBoard(memberNo, questionNo, markedNo);

        return ResponseEntity.ok(readScrapBoardDTO);
    }

    /* 스크랩 저장 */
    @PostMapping("/create")
    @ResponseBody
    public String create( @RequestBody CreateScrapDTO createScrapDTO){
        Long memberNo = 1L;

        scrapService.createScrap(createScrapDTO, memberNo);

        return "scrap/myScrapPage";
    }

    /* 스크랩 취소(삭제) */
    @DeleteMapping("/cancel")
    @ResponseBody
    public ResponseEntity<CancelScrapResponseDTO> cancel(@RequestBody CancelScrapRequestDTO cancelScrapRequestDTO){
        CancelScrapResponseDTO cancelScrapResponseDTO = scrapService.cancelScrap(cancelScrapRequestDTO);

        return ResponseEntity.ok(cancelScrapResponseDTO);
    }
}
