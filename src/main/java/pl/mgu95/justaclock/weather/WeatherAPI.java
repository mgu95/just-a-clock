package pl.mgu95.justaclock.weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Scanner;

public class WeatherAPI implements Weather{
    //All data is in km/h, Celsius scale and 24h clock

    private String key;
    private String city;
    private int currentTemperature;
    private String currentWeatherCondition;
    private String currentWeatherConditionIcon;
    private int[] hourlyForecastTemperature = new int[24]; //temperature for example h 13:00 == array[13]
    private String[] hourlyForecastIcons = new String[24]; //icon for example h 23:00 == array[23]
    private float airQualityPM2_5;
    private float airQualityPM10;
    private String windDirection;
    private int windSpeed;
    private String sunrise;
    private String sunset;
    private int[] dailyForecastTemperature = new int[3];
    private String[] dailyForecastIcons = new String[3];
    private String updateTime;
    private final String dataSource = "https://www.weatherapi.com/";


    public WeatherAPI() {
    }

    private boolean updateWeather() {

        if (key == null || city == null) {
            return false;
        }

//        Do odkomentowania jak wszystko będzie elegancko śmigać :)
//        JSONObject data = getData("http://api.weatherapi.com/v1/forecast.json?key=" + key + "&q=" + city
//                + "&days=3&aqi=yes&alerts=no");
        JSONObject data = new JSONObject(getMyString()); // usunać jak będzie działać

        JSONObject current = data.getJSONObject("current");
        currentTemperature = current.getInt("temp_c");
        windDirection = current.getString("wind_dir");
        windSpeed = current.getInt("wind_kph");
        updateTime = current.getString("last_updated");

        JSONObject currentCondition = current.getJSONObject("condition");
        currentWeatherCondition = currentCondition.getString("text");
        currentWeatherConditionIcon = currentCondition.getString("icon");

        JSONObject airQuality = current.getJSONObject("air_quality");
        airQualityPM2_5 = airQuality.getFloat("pm2_5");
        airQualityPM10 = airQuality.getFloat("pm10");

        JSONObject forecast = data.getJSONObject("forecast");
        JSONArray forecastday = forecast.getJSONArray("forecastday");

        for (int i = 0; i < forecastday.length(); i++) {
            if (i == 0) {
                JSONObject day = forecastday.getJSONObject(i);
                JSONObject astro = day.getJSONObject("astro");
                sunset = amPmTo24h(astro.getString("sunset"));
                sunrise = amPmTo24h(astro.getString("sunrise"));
                JSONArray hours = day.getJSONArray("hour");
                for (int j = 0; j < hours.length(); j++) {
                    JSONObject hour = hours.getJSONObject(j);
                    int h = Integer.parseInt(hour.getString("time").substring(11, 13));
                    hourlyForecastTemperature[j] = hour.getInt("temp_c");
                    JSONObject hourCondition = hour.getJSONObject("condition");
                    hourlyForecastIcons[j] = hourCondition.getString("icon");
                }
            } else {
                JSONObject day = forecastday.getJSONObject(i);
                JSONArray hours = day.getJSONArray("hour");
                for (int j = 0; j < hours.length(); j++) {
                    JSONObject hour = hours.getJSONObject(j);
                    if (hour.getString("time").substring(11, 16).equals("12:00")) {
                        dailyForecastTemperature[i - 1] = hour.getInt("temp_c");
                        JSONObject hourCondition = hour.getJSONObject("condition");
                        dailyForecastIcons[i - 1] = hourCondition.getString("icon");
                    }
                }
            }
        }

        return true;
    }

    private String amPmTo24h(String input) {
        if (input.substring(6, 8).equals("AM")) {
            return input.substring(0, 5);
        } else {
            int i = Integer.parseInt(input.substring(0, 2)) + 12;
            return i + input.substring(2, 5);
        }
    }

    public String getMyString() {

        try {
            File myObj = new File("data.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                return data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
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
    public int getCurrentTemperature() {
        validator();
        return currentTemperature;
    }

    @Override
    public String getCurrentWeatherCondition() {
        validator();
        return currentWeatherCondition;
    }

    @Override
    public String getCurrentWeatherConditionIcon() {
        validator();
        return currentWeatherConditionIcon;
    }

    @Override
    public int getTemperatureAtHour(int hour) {
        validator();
        return hourlyForecastTemperature[hour];
    }

    @Override
    public String getConditionIconAtHour(int hour) {
        validator();
        return hourlyForecastIcons[hour];
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
    public String getWindDirection() {
        validator();
        return windDirection;
    }

    @Override
    public int getWindSpeed() {
        validator();
        return windSpeed;
    }

    @Override
    public String getSunrise() {
        validator();
        return sunrise;
    }

    @Override
    public String getSunset() {
        validator();
        return sunset;
    }

    @Override
    public int getTemperatureAtDay(int day) {
        validator();
        return dailyForecastTemperature[day];
    }

    @Override
    public String getConditionIconAtDay(int day) {
        validator();
        return dailyForecastIcons[day];
    }

    @Override
    public String getUpdateTime() {
        return updateTime;
    }

    @Override
    public String getDataSource() {
        return dataSource;
    }

    @Override
    public String toString() {
        return "WeatherAPI{\n" +
                "   key='" + key + "'\n" +
                "   city='" + city + "'\n" +
                "   currentTemperature=" + currentTemperature + "'\n" +
                "   currentWeatherCondition='" + currentWeatherCondition + "'\n" +
                "   currentWeatherConditionIcon='" + currentWeatherConditionIcon + "'\n" +
                "   hourlyForecastTemperature='" + Arrays.toString(hourlyForecastTemperature) + "'\n" +
                "   hourlyForecastIcons=" + Arrays.toString(hourlyForecastIcons) + "'\n" +
                "   airQualityPM25=" + airQualityPM2_5 + "'\n" +
                "   airQualityPM10=" + airQualityPM10 + "'\n" +
                "   windDirection='" + windDirection + "'\n" +
                "   windSpeed=" + windSpeed + "'\n" +
                "   sunrise='" + sunrise + "'\n" +
                "   sunset='" + sunset + "'\n" +
                "   dailyForecastTemperature='" + Arrays.toString(dailyForecastTemperature) + "'\n" +
                "   dailyForecastIcons=" + Arrays.toString(dailyForecastIcons) + "'\n" +
                "   updateTime='" + updateTime + "'\n" +
                "   dataSource='" + dataSource + "'\n" +
                '}';
    }
}
