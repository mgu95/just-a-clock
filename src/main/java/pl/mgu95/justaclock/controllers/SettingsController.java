package pl.mgu95.justaclock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"key", "city"})
public class SettingsController {

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String showSettings() {
        return "settings";
    }

    @RequestMapping(value = "/settings/weatherInitialization", method = RequestMethod.POST)
    public String weatherInitialization(ModelMap model, @RequestParam String key, @RequestParam String city) {
        model.put("key",  key);
        model.put("city", city);

        return "settings";
    }
}
