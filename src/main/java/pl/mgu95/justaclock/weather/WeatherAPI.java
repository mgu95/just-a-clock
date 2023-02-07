package pl.mgu95.justaclock.weather;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class WeatherAPI implements Weather{

    private String city;
    private int temperature;
    private String condition;
    private String conditionIcon;

    public WeatherAPI() {
        updateWeather();
    }

    @Override
    public void updateWeather() {
        //String stream = stream(new URL("http://api.weatherapi.com/v1/current.json?key=<KEY>&q=<CITY>"));
        String stream = "{\"location\":{\"name\":\"Wroclaw\",\"region\":\"\",\"country\":\"Poland\",\"lat\":51.1,\"lon\":17.03,\"tz_id\":\"Europe/Warsaw\",\"localtime_epoch\":1675771701,\"localtime\":\"2023-02-07 13:08\"},\"current\":{\"last_updated_epoch\":1675771200,\"last_updated\":\"2023-02-07 13:00\",\"temp_c\":0.0,\"temp_f\":32.0,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":3.8,\"wind_kph\":6.1,\"wind_degree\":300,\"wind_dir\":\"WNW\",\"pressure_mb\":1042.0,\"pressure_in\":30.77,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":64,\"cloud\":50,\"feelslike_c\":-0.8,\"feelslike_f\":30.5,\"vis_km\":10.0,\"vis_miles\":6.0,\"uv\":2.0,\"gust_mph\":1.8,\"gust_kph\":2.9}}";
        JSONObject originalJson = new JSONObject(stream);

        JSONObject location = originalJson.getJSONObject("location");
        city = location.getString("name");

        JSONObject current = originalJson.getJSONObject("current");

        temperature = current.getInt("temp_c");

        JSONObject condition = current.getJSONObject("condition");
        this.condition = condition.getString("text");
        conditionIcon = condition.getString("icon");


        System.out.println("city=" + city + "\ntemperature=" + temperature + "\ncondition=" + this.condition + "\nconditionIcon=" + conditionIcon);
    }

    public void printKeySet(JSONObject json) {
        for (String key : json.keySet()) {
            System.out.print(key + ", ");
        }
    }

    public String getConditionIcon() {
        return conditionIcon;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getWeatherCondition() {
        return condition;
    }

    @Override
    public int getTemperature() {
        return temperature;
    }

    @Override
    public String getWeatherConditionForDay(int i) {
        return null;
    }

    @Override
    public int getTemperatureForDay(int i) {
        return 0;
    }

    private String stream(URL url) {
        try (InputStream input = url.openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            return json.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
