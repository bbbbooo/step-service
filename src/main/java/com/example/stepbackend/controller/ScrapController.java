package com.example.stepbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class ScrapController {
    @GetMapping("")
    public String get(){

        return "testPage";
    }

//    @PostMapping("/{questionNo}/scrap")
//    public String create(@PathVariable Long questionNo, ){
//
//
//        return null;
//    }
}
