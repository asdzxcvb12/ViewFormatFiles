package ts.restfultest;

import java.util.ArrayList;

/**
 * Created by user on 2017-03-04.
 */
public class WeatherDataModel {

    private Weather weather;

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

}
class Weather{

    public ArrayList<Hourly> getHourly() {
        return hourly;
    }

    public void setHourly(ArrayList<Hourly> hourly) {
        this.hourly = hourly;
    }

    private ArrayList<Hourly> hourly;

}
class Hourly{
    private Grid grid;
    private Sky sky;
    private Temperature temperature;
    public Grid getGrid() {
        return grid;
    }
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    public Sky getSky() {
        return sky;
    }
    public void setSky(Sky sky) {
        this.sky = sky;
    }
    public Temperature getTemperature() {
        return temperature;
    }
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }
    public String getHumidity() {
        return humidity;
    }
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    private String humidity;
}
class Grid{
    private String village;

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }
}
class Sky {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class Temperature{
    private String tc;

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }
}