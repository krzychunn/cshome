package com.krzysztofnowak.entity;

/**
 * Created by Krzychu on 29.12.2016.
 */

import javax.persistence.*;

import java.util.Date;

@Entity
public class Measurement {

    public Measurement() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String lightIntensity;

    private String gasDensity;

    private String temperature;

    private String humidity;

    private Date timeStamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(String lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    public String getGasDensity() {
        return gasDensity;
    }

    public void setGasDensity(String gasDensity) {
        this.gasDensity = gasDensity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return String.format(
                "{measurement: {id='%d'," +
                        " lightIntensity='%s'," +
                        " gasDensity='%s'," +
                        " temperature='%s'," +
                        " humidity='%s'," +
                        " timeStamp='%s'}}",
                id, lightIntensity, gasDensity, temperature, humidity, timeStamp);
    }
}

