package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.statistics.ReadStatisticsDTO;
import com.example.stepbackend.aggregate.dto.statistics.TodayStatisticsDTO;
import com.example.stepbackend.aggregate.dto.statistics.TotalStatisticsDTO;
import com.example.stepbackend.aggregate.dto.statistics.WeekStatisticsDTO;
import com.example.stepbackend.global.common.annotation.CurrentUser;
import com.example.stepbackend.global.security.token.UserPrincipal;
import com.example.stepbackend.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    // 유형 별 정답률
    private final StatisticsService statisticsService;

    @GetMapping("/myPage/myStatistics")
    public ModelAndView statisics(ModelAndView mv, @CurrentUser UserPrincipal userPrincipal) {

        Long userId =  userPrincipal.getId();
        List<ReadStatisticsDTO> results = statisticsService.getStatisticsByUser(userId);
        TodayStatisticsDTO todayStatistic = statisticsService.getTodayStatistic(userId);
        WeekStatisticsDTO weekStatistic = statisticsService.getWeekStatistic(userId);
        TotalStatisticsDTO totalStatistic = statisticsService.getTotalStatistic(userId);

        mv.addObject("results", results);
        mv.addObject("todayStatistic", todayStatistic);
        mv.addObject("weekStatistic", weekStatistic);
        mv.addObject("totalStatistic", totalStatistic);
        mv.setViewName("statistics/myStatisticsPage");

        return mv;
    }
}
