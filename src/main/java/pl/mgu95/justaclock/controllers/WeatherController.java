package pl.mgu95.justaclock.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.mgu95.justaclock.weather.Weather;
import pl.mgu95.justaclock.weather.WeatherAPI;

@RestController
public class WeatherController {

    Weather weather = new WeatherAPI();

    @RequestMapping(value = "/weather/getCity", method = RequestMethod.GET)
    public String getCity() {
        return weather.getCity();
    }

    @RequestMapping(value = "/weather/getTemperature", method = RequestMethod.GET)
    public String getTemperature() {
        return String.valueOf(weather.getTemperature());
    }

    @RequestMapping(value = "/weather/getCondition", method = RequestMethod.GET)
    public String getWeatherCondition() {
        return weather.getWeatherCondition();
    }

    @RequestMapping(value = "/weather/getConditionIcon", method = RequestMethod.GET)
    public String getConditionIcon() {
        return weather.getConditionIcon();
    }

    @RequestMapping(value = "/weather/updateSettings", method = RequestMethod.POST)
    public boolean updateSettings() {
        System.out.println("POST POST POST");
        return true;
    }
}
