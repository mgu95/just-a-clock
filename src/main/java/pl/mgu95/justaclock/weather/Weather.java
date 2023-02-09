package pl.mgu95.justaclock.weather;

public interface Weather {

    public void setKey(String key);
    public void setCity(String city);
    public String getCity();
    public int getCurrentTemperature();
    public String getCurrentWeatherCondition();
    public String getCurrentWeatherConditionIcon();
    public int getTemperatureAtHour(int hour);
    public String getConditionIconAtHour(int hour);
    public float getAirQualityPM2_5();
    public float getAirQualityPM10();
    public String getWindDirection();
    public int getWindSpeed();
    public String getSunrise();
    public String getSunset();
    public int getTemperatureAtDay(int day);
    public String getConditionIconAtDay(int day);
    public String getUpdateTime();
    public String getDataSource();

}
