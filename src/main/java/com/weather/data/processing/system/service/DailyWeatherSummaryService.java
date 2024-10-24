package com.weather.data.processing.system.service;


import org.springframework.stereotype.Service;

import com.weather.data.processing.system.model.WeatherData;
import com.weather.data.processing.system.repository.WeatherRepository;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class DailyWeatherSummaryService {

    private final WeatherRepository weatherRepository;

    public DailyWeatherSummaryService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public void generateDailySummary() {
        List<WeatherData> data = weatherRepository.findTodayWeatherData();
        
        OptionalDouble avgTemp = data.stream().mapToDouble(WeatherData::getTemp).average();
        double maxTemp = data.stream().mapToDouble(WeatherData::getTemp).max().orElse(Double.NaN);
        double minTemp = data.stream().mapToDouble(WeatherData::getTemp).min().orElse(Double.NaN);
        String dominantCondition = data.stream()
            .map(WeatherData::getMainCondition)
            .reduce((prev, next) -> prev.equals(next) ? prev : next) // Simplified logic for dominant condition
            .orElse("Unknown");

        System.out.printf("Daily Summary: Avg Temp: %.2f, Max Temp: %.2f, Min Temp: %.2f, Dominant: %s%n", 
                          avgTemp.orElse(Double.NaN), maxTemp, minTemp, dominantCondition);
    }
}

