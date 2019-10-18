package com.krzysztofnowak.service;

import com.krzysztofnowak.entity.Light;
import com.krzysztofnowak.repository.LightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzychu on 02.03.2017.
 */

@Component
public class LightService {

    private LightRepository lightRepository;

    private int rowsCounter;
    private int rowsLimit = 100;

    @Autowired
    public LightService(LightRepository repository) {
        this.lightRepository = repository;
        this.rowsCounter = countAll();
        deleteRowsOverLimit();
    }

    public int countAll(){
        return (int) lightRepository.count();
    }

    public List<Light> findAll(){
        return this.lightRepository.findAll();
    }

    public void save(Light light){
        lightRepository.save(light);
        this.rowsCounter++;
        deleteRowsOverLimit();
    }

    public Light findLastBuzzerState(){
        return lightRepository.findLastLightState();
    }

    public void deleteRowsOverLimit(){
        if(this.rowsCounter >= rowsLimit) {
            lightRepository.deleteFirstLight();
            this.rowsCounter--;
        }
    }
}
