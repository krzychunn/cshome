package com.krzysztofnowak.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Krzychu on 31.12.2016.
 */

@Entity
public class MaximumGas {

    public MaximumGas() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String maximumGas;

    private Date timeStamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaximumGas() {
        return maximumGas;
    }

    public void setMaximumGas(String minimumGas) {
        this.maximumGas = minimumGas;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
