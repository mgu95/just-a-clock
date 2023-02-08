package pl.mgu95.justaclock.controllers;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.mgu95.justaclock.weather.Weather;
import pl.mgu95.justaclock.weather.WeatherAPI;

@RestController
@SessionAttributes({"key", "city"})
public class WeatherController {

    Weather weather = new WeatherAPI();

    @RequestMapping(value = "/weather/getCity", method = RequestMethod.GET)
    public String getCity() {
        return weather.getCity();
    }

    @RequestMapping(value = "/weather/getTemperature", method = RequestMethod.GET)
    public String getTemperature() {
        //return String.valueOf(weather.getTemperature());
        return null;
    }

    @RequestMapping(value = "/weather/getCondition", method = RequestMethod.GET)
    public String getWeatherCondition() {
        //return weather.getWeatherCondition();
        return null;
    }

    @RequestMapping(value = "/weather/getConditionIcon", method = RequestMethod.GET)
    public String getConditionIcon() {
        //return weather.getConditionIcon();
        return null;
    }

    @RequestMapping(value = "/weather/getUpdateTime", method = RequestMethod.GET)
    public String getUpdateTime() {
        return weather.getUpdateTime();
    }

}
