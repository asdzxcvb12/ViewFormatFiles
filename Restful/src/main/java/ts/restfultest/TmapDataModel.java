package ts.restfultest;

import java.util.ArrayList;

/**
 * Created by user on 2017-03-04.
 */
public class TmapDataModel {

    private ArrayList<Features> features;

    public ArrayList<Features> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Features> features) {
        this.features = features;
    }
}

class Features {
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}

class Properties {
    private int totalDistance;
    private int totalTime;
    private int totalFare;
    private int taxiFare;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(int totalFare) {
        this.totalFare = totalFare;
    }

    public int getTaxiFare() {
        return taxiFare;
    }

    public void setTaxiFare(int taxiFare) {
        this.taxiFare = taxiFare;
    }
}