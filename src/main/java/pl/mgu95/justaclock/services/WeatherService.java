package pl.mgu95.justaclock.services;

import org.springframework.stereotype.Service;
import pl.mgu95.justaclock.weather.Weather;
import pl.mgu95.justaclock.weather.WeatherAPI.WeatherAPI;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class WeatherService {

    Weather weather = new WeatherAPI();

    public void setKey(String key) {
        weather.setKey(key);
    }

    public void setCity(String city) {
        weather.setCity(city);
    }

    public String getCity() {
        return weather.getCity();
    }

    public int getTemperature(LocalDateTime localDateTime) {
        return weather.getTemperature(localDateTime);
    }

    public String getWeatherCondition(LocalDateTime localDateTime) {
        return weather.getWeatherCondition(localDateTime);
    }

    public String getWeatherIcon(LocalDateTime localDateTime) {
        return weather.getWeatherIcon(localDateTime);
    }

    public String getSunrise(LocalDate localDate) {
        return weather.getSunrise(localDate);
    }

    public String getSunset(LocalDate localDate) {
        return weather.getSunset(localDate);
    }

    public String getWindDirection(LocalDateTime localDateTime) {
        return weather.getWindDirection(localDateTime);
    }

    public int getWindSpeed(LocalDateTime localDateTime) {
        return weather.getWindSpeed(localDateTime);
    }

    public float getAirQualityPM2_5() {
        return weather.getAirQualityPM2_5();
    }

    public float getAirQualityPM10() {
        return weather.getAirQualityPM10();
    }

    public String getUpdateTime() {
        return weather.getUpdateTime();
    }

    public String getDataSource() {
        return weather.getDataSource();
    }
}
