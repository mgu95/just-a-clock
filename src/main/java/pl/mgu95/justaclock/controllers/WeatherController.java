package pl.mgu95.justaclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.mgu95.justaclock.services.WeatherService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@Validated
@RequestMapping("/weather")
public class WeatherController {

    WeatherService weatherService;

    @Autowired
    public void setUp(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/getCity")
    public String getCity() {
        return weatherService.getCity();
    }

    @GetMapping("/getTemperature")
    public int getTemperature(@RequestParam int day, @RequestParam int month, @RequestParam int year,
                              @RequestParam @Min(0) @Max(23) int hour) {
        return weatherService.getTemperature(LocalDateTime.of(year, month, day, hour, 0));
    }

    @RequestMapping(value = "/getWeatherCondition/day={day}&month={month}&year={year}&hour={hour}", method = RequestMethod.GET)
    public String getWeatherCondition(@PathVariable int day, @PathVariable int month, @PathVariable int year, @PathVariable int hour) {
        return weatherService.getWeatherCondition(LocalDateTime.of(year, month, day, hour, 0));
    }

    @RequestMapping(value = "/getWeatherIcon/day={day}&month={month}&year={year}&hour={hour}", method = RequestMethod.GET)
    public String getWeatherIcon(@PathVariable int day, @PathVariable int month, @PathVariable int year, @PathVariable int hour) {
        return weatherService.getWeatherIcon(LocalDateTime.of(year, month, day, hour, 0));
    }

    @RequestMapping(value = "/getSunrise/day={day}&month={month}&year={year}", method = RequestMethod.GET)
    public String getSunrise(@PathVariable int day, @PathVariable int month, @PathVariable int year) {
        return weatherService.getSunrise(LocalDate.of(year, month, day));
    }

    @RequestMapping(value = "/getSunset/day={day}&month={month}&year={year}", method = RequestMethod.GET)
    public String getSunset(@PathVariable int day, @PathVariable int month, @PathVariable int year) {
        return weatherService.getSunset(LocalDate.of(year, month, day));
    }

    @RequestMapping(value = "/getWindDirection/day={day}&month={month}&year={year}&hour={hour}", method = RequestMethod.GET)
    public String getWindDirection(@PathVariable int day, @PathVariable int month, @PathVariable int year, @PathVariable int hour) {
        return weatherService.getWindDirection(LocalDateTime.of(year, month, day, hour, 0));
    }

    @RequestMapping(value = "/getWindSpeed/day={day}&month={month}&year={year}&hour={hour}", method = RequestMethod.GET)
    public int getWindSpeed(@PathVariable int day, @PathVariable int month, @PathVariable int year, @PathVariable int hour) {
        return weatherService.getWindSpeed(LocalDateTime.of(year, month, day, hour, 0));
    }

    @RequestMapping(value = "/getAirQualityPM2_5", method = RequestMethod.GET)
    public float getAirQualityPM2_5() {
        return weatherService.getAirQualityPM2_5();
    }

    @RequestMapping(value = "/getAirQualityPM10", method = RequestMethod.GET)
    public float getAirQualityPM10() {
        return weatherService.getAirQualityPM10();
    }

    @RequestMapping(value = "/getUpdateTime", method = RequestMethod.GET)
    public String getUpdateTime() {
        return weatherService.getUpdateTime();
    }

    @RequestMapping(value = "/getDataSource", method = RequestMethod.GET)
    public String getDataSource() {
        return weatherService.getDataSource();
    }

}
