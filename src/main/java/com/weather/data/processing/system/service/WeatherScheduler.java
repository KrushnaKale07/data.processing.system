package com.weather.data.processing.system.service;

import com.weather.data.processing.system.model.WeatherData;
import com.weather.data.processing.system.repository.WeatherRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WeatherScheduler {

	private final WeatherService weatherService;
	private final WeatherRepository weatherRepository;

	@Value("${scheduler.fixedRate}")
	private long fixedRate;

	public WeatherScheduler(WeatherService weatherService, WeatherRepository weatherRepository) {
		this.weatherService = weatherService;
		this.weatherRepository = weatherRepository;
	}

	@Scheduled(fixedRateString = "${scheduler.fixedRate}")
	public void fetchWeatherData() {
		List<String> cities = Arrays.asList("Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad");

		cities.forEach(city -> {
			WeatherData weatherData = weatherService.getWeatherData(city);
			weatherRepository.save(weatherData);
		});
	}
}
