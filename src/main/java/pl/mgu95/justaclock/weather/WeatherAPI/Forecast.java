package pl.mgu95.justaclock.weather.WeatherAPI;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;

public class Forecast {

    private LocalDate date;
    private String sunrise;
    private String sunset;
    private int[] temperatures = new int[24];
    private String[] icons = new String[24];
    private String[] conditions = new String[24];
    private int[] windSpeeds = new int[24];
    private String[] windDirections = new String[24];


    public Forecast(JSONObject json) {
        date = setDate(json.getString("date"));
        JSONObject astro = json.getJSONObject("astro");
        sunset = amPmTo24h(astro.getString("sunset"));
        sunrise = amPmTo24h(astro.getString("sunrise"));
        JSONArray hours = json.getJSONArray("hour");
        for (int i = 0; i < hours.length(); i++) {
            JSONObject hour = hours.getJSONObject(i);
            temperatures[i] = hour.getInt("temp_c");
            windSpeeds[i] = hour.getInt("wind_kph");
            windDirections[i] = hour.getString("wind_dir");
            JSONObject condition = hour.getJSONObject("condition");
            icons[i] = condition.getString("icon");
            conditions[i] = condition.getString("text");
        }
    }

    private LocalDate setDate(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date. substring(8, 10));
        LocalDate localDate = LocalDate.of(year, month, day);
        return localDate;
    }

    private String amPmTo24h(String input) {
        if (input.substring(6, 8).equals("AM")) {
            return input.substring(0, 5);
        } else {
            int i = Integer.parseInt(input.substring(0, 2)) + 12;
            return i + input.substring(2, 5);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public int getTemperatureAt(int hour) {
        return temperatures[hour];
    }

    public String getIconAt(int hour) {
        return icons[hour];
    }

    public String getConditionAt(int hour) {
        return conditions[hour];
    }

    public int getWindSpeedAt(int hour) {
        return windSpeeds[hour];
    }

    public String getWindDirectionAt(int hour) {
        return windDirections[hour];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Forecast{\n");
        builder.append("   date=" + date + "\n");
        builder.append("   sunrise=" + sunrise + "\n");
        builder.append("   sunset=" + sunset + "\n");
        for (int i = 0; i < 23; i++) {
            builder.append("   forecastAtHour=" + i + ":00\n");
            builder.append("      temperature=" + temperatures[i] + "\n");
            builder.append("      condition=" + conditions[i] + "\n");
            builder.append("      icon=" + icons[i] + "\n");
            builder.append("      windSpeed=" + windSpeeds[i] + "\n");
            builder.append("      windDirection=" + windDirections[i] + "\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
