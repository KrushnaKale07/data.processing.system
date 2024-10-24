package com.weather.data.processing.system.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.weather.data.processing.system.model.WeatherData;

@Service
public class AlertService {

    @Value("${alert.threshold.temp}")
    private double tempThreshold;

    public void checkForAlerts(WeatherData weatherData) {
        if (weatherData.getTemp() > tempThreshold) {
            // Trigger alert (e.g., send email or log it)
            System.out.printf("ALERT: High temperature detected in %s: %.2f°C%n", weatherData.getCity(), weatherData.getTemp());
        }
    }
}

