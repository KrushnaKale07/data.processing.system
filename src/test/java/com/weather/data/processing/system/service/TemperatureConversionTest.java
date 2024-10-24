package com.weather.data.processing.system.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TemperatureConversionTest {

	@Test
	public void testKelvinToCelsiusConversion() {
		// Given
		double kelvinTemp = 300.0;

		// When
		double celsiusTemp = kelvinToCelsius(kelvinTemp);

		// Then
		assertEquals(26.85, celsiusTemp, 0.01);
	}

	@Test
	public void testKelvinToFahrenheitConversion() {
		// Given
		double kelvinTemp = 300.0;

		// When
		double fahrenheitTemp = kelvinToFahrenheit(kelvinTemp);

		// Then
		assertEquals(80.33, fahrenheitTemp, 0.01);
	}

	private double kelvinToCelsius(double kelvin) {
		return kelvin - 273.15;
	}

	private double kelvinToFahrenheit(double kelvin) {
		return (kelvin - 273.15) * 9 / 5 + 32;
	}
}
