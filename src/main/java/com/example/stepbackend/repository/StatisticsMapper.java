package com.example.stepbackend.repository;

import com.example.stepbackend.aggregate.dto.statistics.ReadStatisticsDTO;
import com.example.stepbackend.aggregate.dto.statistics.TodayStatisticsDTO;
import com.example.stepbackend.aggregate.dto.statistics.TotalStatisticsDTO;
import com.example.stepbackend.aggregate.dto.statistics.WeekStatisticsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsMapper {

    List<ReadStatisticsDTO> findStatisticsByUser(Long memberNo);

    TodayStatisticsDTO findTodayStatistics(Long userId);

    WeekStatisticsDTO findWeekStatistics(Long userId);

    TotalStatisticsDTO findTotalStatistics(Long userId);
}
