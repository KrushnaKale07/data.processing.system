package com.weather.data.processing.system.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.weather.data.processing.system.model.WeatherData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WeatherServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    public void testApiConnectivity() {
        // Given
        String mockCity = "Delhi";
        String mockApiResponse = "{\"main\":{\"temp\":303.15,\"feels_like\":305.0},\"weather\":[{\"main\":\"Clear\"}]}";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + mockCity + "&appid=your-api-key&units=metric";
        
        // When
        when(restTemplate.getForObject(url, String.class)).thenReturn(mockApiResponse);
        WeatherData weatherData = weatherService.getWeatherData(mockCity);
        
        // Then
        assertNotNull(weatherData);
        assertEquals(mockCity, weatherData.getCity());
        assertEquals(303.15, weatherData.getTemp());
        assertEquals(305.0, weatherData.getFeelsLike());
        assertEquals("Clear", weatherData.getMainCondition());
    }
}
