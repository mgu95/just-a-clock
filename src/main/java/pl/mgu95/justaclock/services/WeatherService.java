package pl.mgu95.justaclock.services;

import org.springframework.stereotype.Service;
import pl.mgu95.justaclock.weather.Weather;
import pl.mgu95.justaclock.weather.WeatherAPI;

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

    public int getCurrentTemperature() {
        return weather.getCurrentTemperature();
    }

    public String getCurrentWeatherCondition() {
        return weather.getCurrentWeatherCondition();
    }

    public String getCurrentWeatherConditionIcon() {
        return weather.getCurrentWeatherConditionIcon();
    }

    public int getTemperatureAtHour(int hour) {
        return weather.getTemperatureAtHour(hour);
    }

    public String getConditionIconAtHour(int hour) {
        return weather.getConditionIconAtHour(hour);
    }

    public float getAirQualityPM2_5() {
        return weather.getAirQualityPM2_5();
    }

    public float getAirQualityPM10() {
        return weather.getAirQualityPM10();
    }

    public String getWindDirection() {
        return weather.getWindDirection();
    }

    public int getWindSpeed() {
        return weather.getWindSpeed();
    }

    public String getSunrise() {
        return weather.getSunrise();
    }

    public String getSunset() {
        return weather.getSunset();
    }

    public int getTemperatureAtDay(int day) {
        return weather.getTemperatureAtDay(day);
    }

    public String getConditionIconAtDay(int day) {
        return weather.getConditionIconAtDay(day);
    }

    public String getUpdateTime() {
        return weather.getUpdateTime();
    }

    public String getDataSource() {
        return weather.getDataSource();
    }
}
