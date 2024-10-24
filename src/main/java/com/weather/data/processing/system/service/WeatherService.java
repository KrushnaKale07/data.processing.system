package com.weather.data.processing.system.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weather.data.processing.system.model.WeatherData;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class WeatherService {

	@Value("${weather.api.key}")
	private String apiKey;

	@Value("${weather.api.url}")
	private String apiUrl;

	private final RestTemplate restTemplate = new RestTemplate();

	public WeatherData getWeatherData(String city) {
		String url = apiUrl.replace("{city}", city).replace("{apiKey}", apiKey);
		Map<String, Object> response = restTemplate.getForObject(url, Map.class);

		Map<String, Object> main = (Map<String, Object>) response.get("main");
		String weatherCondition = (String) ((Map<String, Object>) ((Map<String, Object>) response.get("weather"))
				.get(0)).get("main");

		WeatherData weatherData = new WeatherData();
		weatherData.setCity(city);
		weatherData.setTemp((Double) main.get("temp"));
		weatherData.setFeelsLike((Double) main.get("feels_like"));
		weatherData.setMainCondition(weatherCondition);
		weatherData.setTimestamp(LocalDateTime.now());

		return weatherData;
	}
}
