package pl.mgu95.justaclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mgu95.justaclock.services.WeatherService;

@Controller
public class SettingsController {

    WeatherService weatherService;

    @Autowired
    public void setUp(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String showSettings() {
        return "settings";
    }

    @RequestMapping(value = "/settings/weatherInitialization", method = RequestMethod.POST)
    public String weatherInitialization(@RequestParam String key, @RequestParam String city) {
        weatherService.setKey(key);
        weatherService.setCity(city);

        return "settings";
    }
}
