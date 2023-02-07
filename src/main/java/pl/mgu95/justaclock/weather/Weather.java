package pl.mgu95.justaclock.weather;

public interface Weather {

    public void updateWeather();

    public void setCity(String city);

    public String getCity();

    public String getWeatherCondition();

    public String getConditionIcon();

    public int getTemperature();

    public String getWeatherConditionForDay(int i);

    public int getTemperatureForDay(int i);
}
