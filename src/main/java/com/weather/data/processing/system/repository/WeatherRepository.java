package com.weather.data.processing.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weather.data.processing.system.model.WeatherData;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {

	@Query("SELECT w FROM WeatherData w WHERE w.timestamp >= CURRENT_DATE")
	List<WeatherData> findTodayWeatherData();
}
