package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.scrap.ScrapDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/v1/scrap")
@RequiredArgsConstructor
public class ScrapController {
    @GetMapping("")
    public String get(){

        return "testPage";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute ScrapDTO scrapDTO){


        return null;
    }
}
