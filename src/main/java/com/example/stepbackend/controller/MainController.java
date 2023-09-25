package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.entity.enumType.Role;
import com.example.stepbackend.global.common.annotation.CurrentUser;
import com.example.stepbackend.global.security.token.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = {"/", "/*"})
public class MainController {

    @GetMapping("/")

    public String main(@CurrentUser UserPrincipal user, Model model) {

        return "main";
    }
}
