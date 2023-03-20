package pl.mgu95.justaclock.weather.WeatherAPI;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.mgu95.justaclock.weather.Weather;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class WeatherAPI implements Weather {

    private String key;
    private String city;
    private Forecast[] forecasts = new Forecast[3];
    private float airQualityPM2_5;
    private float airQualityPM10;
    private String updateTime;
    private final String dataSource = "https://www.weatherapi.com/";


    public WeatherAPI() {
    }

    private void updateWeather() {
        JSONObject data = getData("http://192.168.0.31:8080/getWeatherData");
//        "http://api.weatherapi.com/v1/forecast.json?key=" + key + "&q=" + city + "&days=3&aqi=yes&alerts=no"
        JSONObject current = data.getJSONObject("current");
        updateTime = current.getString("last_updated");
        JSONObject airQuality = current.getJSONObject("air_quality");
        airQualityPM2_5 = airQuality.getFloat("pm2_5");
        airQualityPM10 = airQuality.getFloat("pm10");
        JSONObject forecast = data.getJSONObject("forecast");
        JSONArray forecastday = forecast.getJSONArray("forecastday");
        for (int i = 0; i < forecasts.length; i++) {
            forecasts[i] = new Forecast(forecastday.getJSONObject(i));
        }
    }

    private JSONObject getData(String address) {
        URL url = null;
        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try (InputStream input = url.openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder data = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                data.append((char) c);
            }
            return new JSONObject(data.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validator() {
        if (city == null || key == null) return false;
        if (updateTime == null) updateWeather();
        LocalDate localDate = LocalDate.now();
        LocalDate updateDate = LocalDate.parse(updateTime.substring(0, 10));
        if (localDate.isAfter(updateDate)) updateWeather();
        LocalTime localTime = LocalTime.now();
        String str = updateTime.substring(11, 16);
        LocalTime updateTime = LocalTime.parse(str);
        if (localTime.getHour() - updateTime.getHour() != 0) updateWeather();
        if (localTime.getMinute() - updateTime.getMinute() > 15) updateWeather();

        return true;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getCity() {
        validator();
        return city;
    }

    @Override
    public int getTemperature(LocalDateTime localDateTime) {
        validator();
        for (Forecast forecast : forecasts) {
            if (forecast.getDate().getDayOfMonth() == localDateTime.getDayOfMonth()) {
                return forecast.getTemperatureAt(localDateTime.getHour());
            }
        }
        return 0;
    }

    @Override
    public String getWeatherCondition(LocalDateTime localDateTime) {
        validator();
        for (Forecast forecast : forecasts) {
            if (forecast.getDate().getDayOfMonth() == localDateTime.getDayOfMonth()) {
                return forecast.getConditionAt(localDateTime.getHour());
            }
        }
        return null;
    }

    public String getWeatherIcon(LocalDateTime localDateTime) {
        validator();
        for (Forecast forecast : forecasts) {
            if (forecast.getDate().getDayOfMonth() == localDateTime.getDayOfMonth()) {
                return forecast.getIconAt(localDateTime.getHour());
            }
        }
        return null;
    }

    @Override
    public String getSunrise(LocalDate localDate) {
        validator();
        return null;
    }

    @Override
    public String getSunset(LocalDate localDate) {
        validator();
        return null;
    }

    @Override
    public String getWindDirection(LocalDateTime localDateTime) {
        validator();
        return null;
    }

    @Override
    public int getWindSpeed(LocalDateTime localDateTime) {
        validator();
        return 0;
    }

    @Override
    public float getAirQualityPM2_5() {
        validator();
        return airQualityPM2_5;
    }

    @Override
    public float getAirQualityPM10() {
        validator();
        return airQualityPM10;
    }

    @Override
    public String getUpdateTime() {
        return updateTime;
    }

    @Override
    public String getDataSource() {
        return dataSource;
    }

}
