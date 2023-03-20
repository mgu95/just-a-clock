package pl.mgu95.justaclock.weather;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Weather {

    public void setKey(String key);
    public void setCity(String city);
    public String getCity();
    public int getTemperature(LocalDateTime localDateTime);
    public String getWeatherCondition(LocalDateTime localDateTime);
    public String getWeatherIcon(LocalDateTime localDateTime);
    public String getSunrise(LocalDate localDate);
    public String getSunset(LocalDate localDate);
    public String getWindDirection(LocalDateTime localDateTime);
    public int getWindSpeed(LocalDateTime localDateTime);
    public float getAirQualityPM2_5();
    public float getAirQualityPM10();
    public String getUpdateTime();
    public String getDataSource();

}
