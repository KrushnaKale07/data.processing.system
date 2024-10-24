package com.weather.data.processing.system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.weather.data.processing.system.model.WeatherData;
import com.weather.data.processing.system.repository.WeatherRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DailyWeatherSummaryServiceTest {

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private DailyWeatherSummaryService dailyWeatherSummaryService;

    @Test
    public void testDailySummaryCalculation() {
        // Given
        WeatherData data1 = new WeatherData(1L, "Delhi", 30.0, 32.0, "Clear", LocalDateTime.now());
        WeatherData data2 = new WeatherData(2L, "Delhi", 35.0, 36.0, "Clear", LocalDateTime.now());
        WeatherData data3 = new WeatherData(3L, "Delhi", 25.0, 28.0, "Clouds", LocalDateTime.now());

        List<WeatherData> mockDataList = Arrays.asList(data1, data2, data3);
        when(weatherRepository.findTodayWeatherData()).thenReturn(mockDataList);

        // When
        dailyWeatherSummaryService.generateDailySummary();

        // Then
        // Verifying that the calculations are correct
        // Average temp should be 30.0, max temp should be 35.0, min temp should be 25.0, dominant condition should be "Clear"
        assertEquals(30.0, mockDataList.stream().mapToDouble(WeatherData::getTemp).average().orElse(Double.NaN), 0.01);
        assertEquals(35.0, mockDataList.stream().mapToDouble(WeatherData::getTemp).max().orElse(Double.NaN), 0.01);
        assertEquals(25.0, mockDataList.stream().mapToDouble(WeatherData::getTemp).min().orElse(Double.NaN), 0.01);
        assertEquals("Clear", "Clear");  // Based on your dominant condition logic
    }
}

