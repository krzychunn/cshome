package com.krzysztofnowak.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Krzychu on 09.02.2017.
 */
@Entity
public class AirCondition {

    public AirCondition() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Boolean state;

    private Date timeStamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
