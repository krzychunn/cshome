package com.krzysztofnowak.service;

import com.krzysztofnowak.entity.MinimumLight;
import com.krzysztofnowak.repository.MinimumLightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzychu on 28.02.2017.
 */
@Component
public class MinimumLightService {

    private MinimumLightRepository minimumLightRepository;

    private int rowsCounter;
    private int rowsLimit = 100;

    @Autowired
    public MinimumLightService(MinimumLightRepository repository) {
        this.minimumLightRepository = repository;
        this.rowsCounter = countAll();
        deleteRowsOverLimit();
    }

    public int countAll(){
        return (int) minimumLightRepository.count();
    }

    public List<MinimumLight> findAll(){
        return this.minimumLightRepository.findAll();
    }

    public void save(MinimumLight minimumLight){
        minimumLightRepository.save(minimumLight);
        this.rowsCounter++;
        deleteRowsOverLimit();
    }

    public MinimumLight findLastMinimumLight(){
        return minimumLightRepository.findLastMinimumLight();
    }

    public void deleteRowsOverLimit(){
        if(this.rowsCounter >= rowsLimit) {
            minimumLightRepository.deleteFirstMinimumLight();
            this.rowsCounter--;
        }
    }

}
