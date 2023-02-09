package pl.mgu95.justaclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.mgu95.justaclock.services.WeatherService;

@RestController
public class WeatherController {

    WeatherService weatherService;

    @Autowired
    public void setUp(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(value = "/weather/getCity", method = RequestMethod.GET)
    public String getCity() {
        return weatherService.getCity();
    }

    @RequestMapping(value = "/weather/getCurrentTemperature", method = RequestMethod.GET)
    public int getCurrentTemperature() {
        return weatherService.getCurrentTemperature();
    }

    @RequestMapping(value = "/weather/getCurrentWeatherCondition", method = RequestMethod.GET)
    public String getCurrentWeatherCondition() {
        return weatherService.getCurrentWeatherCondition();
    }

    @RequestMapping(value = "/weather/getCurrentWeatherConditionIcon", method = RequestMethod.GET)
    public String getCurrentWeatherConditionIcon() {
        return weatherService.getCurrentWeatherConditionIcon();
    }

    @RequestMapping(value = "/weather/getTemperatureAtHour/{hour}", method = RequestMethod.GET)
    public int getTemperatureAtHour(@PathVariable int hour) {
        return weatherService.getTemperatureAtHour(hour);
    }

    @RequestMapping(value = "/weather/getConditionIconAtHour/{hour}", method = RequestMethod.GET)
    public String getConditionIconAtHour(@PathVariable int hour) {
        return weatherService.getConditionIconAtHour(hour);
    }

    @RequestMapping(value = "/weather/getAirQualityPM2_5", method = RequestMethod.GET)
    public float getAirQualityPM2_5() {
        return weatherService.getAirQualityPM2_5();
    }

    @RequestMapping(value = "/weather/getAirQualityPM10", method = RequestMethod.GET)
    public float getAirQualityPM10() {
        return weatherService.getAirQualityPM10();
    }

    @RequestMapping(value = "/weather/getWindDirection", method = RequestMethod.GET)
    public String getWindDirection() {
        return weatherService.getWindDirection();
    }

    @RequestMapping(value = "/weather/getWindSpeed", method = RequestMethod.GET)
    public int getWindSpeed() {
        return weatherService.getWindSpeed();
    }

    @RequestMapping(value = "/weather/getSunrise", method = RequestMethod.GET)
    public String getSunrise() {
        return weatherService.getSunrise();
    }

    @RequestMapping(value = "/weather/getSunset", method = RequestMethod.GET)
    public String getSunset() {
        return weatherService.getSunset();
    }

    @RequestMapping(value = "/weather/getTemperatureAtDay/{day}", method = RequestMethod.GET)
    public int getTemperatureAtDay(int day) {
        return weatherService.getTemperatureAtDay(day);
    }

    @RequestMapping(value = "/weather/getConditionIconAtDay/{day}", method = RequestMethod.GET)
    public String getConditionIconAtDay(int day) {
        return weatherService.getConditionIconAtDay(day);
    }

    @RequestMapping(value = "/weather/getUpdateTime", method = RequestMethod.GET)
    public String getUpdateTime() {
        return weatherService.getUpdateTime();
    }

    @RequestMapping(value = "/weather/getDataSource", method = RequestMethod.GET)
    public String getDataSource() {
        return weatherService.getDataSource();
    }

}
