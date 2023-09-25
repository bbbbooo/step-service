package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.statistics.ReadStatisticsDTO;
import com.example.stepbackend.aggregate.dto.statistics.TodayStatisticsDTO;
import com.example.stepbackend.aggregate.dto.statistics.TotalStatisticsDTO;
import com.example.stepbackend.aggregate.dto.statistics.WeekStatisticsDTO;
import com.example.stepbackend.repository.StatisticsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsMapper statisticsMapper;

    public List<ReadStatisticsDTO> getStatisticsByUser(Long userId) {

        List<ReadStatisticsDTO> results = statisticsMapper.findStatisticsByUser(userId);

        return results;
    }

    public TodayStatisticsDTO getTodayStatistic(Long userId) {

        TodayStatisticsDTO result =  statisticsMapper.findTodayStatistics(userId);

        return result;
    }

    public WeekStatisticsDTO getWeekStatistic(Long userId) {

        WeekStatisticsDTO result = statisticsMapper.findWeekStatistics(userId);

        return result;
    }

    public TotalStatisticsDTO getTotalStatistic(Long userId) {

        TotalStatisticsDTO result = statisticsMapper.findTotalStatistics(userId);

        return result;
    }
}
