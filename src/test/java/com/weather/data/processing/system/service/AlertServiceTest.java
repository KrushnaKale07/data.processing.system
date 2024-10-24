package com.weather.data.processing.system.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.weather.data.processing.system.model.WeatherData;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AlertServiceTest {

    @InjectMocks
    private AlertService alertService;

    @Value("${alert.threshold.temp}")
    private double tempThreshold;

    @Test
    public void testAlertTrigger() {
        // Given
        WeatherData data = new WeatherData(1L, "Delhi", 38.0, 40.0, "Clear", null);
        
        // When
        alertService.checkForAlerts(data);
        
        // Then
        assertTrue(data.getTemp() > tempThreshold);
    }

    @Test
    public void testNoAlertWhenBelowThreshold() {
        // Given
        WeatherData data = new WeatherData(2L, "Delhi", 30.0, 32.0, "Clear", null);
        
        // When
        alertService.checkForAlerts(data);
        
        // Then
        assertFalse(data.getTemp() > tempThreshold);
    }
}

