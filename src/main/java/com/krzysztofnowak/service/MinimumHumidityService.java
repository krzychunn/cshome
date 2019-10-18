package com.krzysztofnowak.service;

import com.krzysztofnowak.entity.MinimumHumidity;
import com.krzysztofnowak.repository.MinimumHumidityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzychu on 28.02.2017.
 */
@Component
public class MinimumHumidityService {

    private MinimumHumidityRepository minimumHumidityRepository;

    private int rowsCounter;
    private int rowsLimit = 100;

    @Autowired
    public MinimumHumidityService(MinimumHumidityRepository repository) {
        this.minimumHumidityRepository = repository;
        this.rowsCounter = countAll();
        deleteRowsOverLimit();
    }

    public int countAll(){
        return (int) minimumHumidityRepository.count();
    }

    public List<MinimumHumidity> findAll(){
        return this.minimumHumidityRepository.findAll();
    }

    public void save(MinimumHumidity minimumHumidity){
        minimumHumidityRepository.save(minimumHumidity);
        this.rowsCounter++;
        deleteRowsOverLimit();
    }

    public MinimumHumidity findLastMinimumHumidity(){
        return minimumHumidityRepository.findLastMinimumHumidity();
    }

    public void deleteRowsOverLimit(){
        if(this.rowsCounter >= rowsLimit) {
            minimumHumidityRepository.deleteFirstMinimumHumidity();
            this.rowsCounter--;
        }
    }
}
