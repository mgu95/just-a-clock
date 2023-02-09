package pl.mgu95.justaclock.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherAPITest {

    WeatherAPI weatherAPI;

    @BeforeEach
    void beforeEach() {
        weatherAPI = new WeatherAPI();
    }

    @Test
    void ifCityAndKeyIsNullUpdateWeatherShouldReturnFalse() {
        boolean result = true;
        assertFalse(result);
    }

    @Test
    void ifCityIsNullUpdateWeatherShouldReturnFalse() {
        weatherAPI.setKey("XXX");
        boolean result = true;
        assertFalse(result);
    }

    @Test
    void ifKeyIsNullUpdateWeatherShouldReturnFalse() {
        weatherAPI.setCity("YYY");
        boolean result = true;
        assertFalse(result);
    }

    @Test
    void ifCityAndKeyIsNotNullUpdateWeatherShouldReturnTrue() {
        weatherAPI.setKey("XXX");
        weatherAPI.setCity("YYY");
        boolean result = false;
        assertTrue(result);
    }

    @Test
    void dataIsOnlyFor3daysNot4() {
        assertTrue(false);
    }
}
