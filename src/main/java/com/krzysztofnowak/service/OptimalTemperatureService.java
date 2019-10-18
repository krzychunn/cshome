package com.krzysztofnowak.service;

import com.krzysztofnowak.entity.OptimalTemperature;
import com.krzysztofnowak.repository.OptimalTemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzychu on 20.01.2017.
 */
@Component
public class OptimalTemperatureService {

    private OptimalTemperatureRepository optimalTemperatureRepository;

    private int rowsCounter;
    private int rowsLimit = 100;

    @Autowired
    public OptimalTemperatureService(OptimalTemperatureRepository repository) {
        this.optimalTemperatureRepository = repository;
        this.rowsCounter = countAll();
        deleteRowsOverLimit();
    }

    public int countAll(){
        return (int) optimalTemperatureRepository.count();
    }

    public List<OptimalTemperature> findAll(){
        return this.optimalTemperatureRepository.findAll();
    }

    public void save(OptimalTemperature optimalTemperature){
        optimalTemperatureRepository.save(optimalTemperature);
        this.rowsCounter++;
        deleteRowsOverLimit();
    }

    public OptimalTemperature findLastOptimalTemperature(){
        return optimalTemperatureRepository.findLastOptimalTemperature();
    }

    public void deleteRowsOverLimit(){
        if(this.rowsCounter >= rowsLimit) {
            optimalTemperatureRepository.deleteFirstOptimalTemperature();
            this.rowsCounter--;
        }
    }
}
