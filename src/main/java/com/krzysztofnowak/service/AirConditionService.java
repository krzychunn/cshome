package com.krzysztofnowak.service;

import com.krzysztofnowak.entity.AirCondition;
import com.krzysztofnowak.repository.AirConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzychu on 09.02.2017.
 */

@Component
public class AirConditionService {

    private AirConditionRepository airConditionRepository;

    private int rowsCounter;
    private int rowsLimit = 100;

    @Autowired
    public AirConditionService(AirConditionRepository repository) {
        this.airConditionRepository = repository;
        this.rowsCounter = countAll();
        deleteRowsOverLimit();
    }

    public int countAll(){
        return (int) airConditionRepository.count();
    }

    public List<AirCondition> findAll(){
        return this.airConditionRepository.findAll();
    }

    public void save(AirCondition buzzer){
        airConditionRepository.save(buzzer);
        this.rowsCounter++;
        deleteRowsOverLimit();
    }

    public AirCondition findLastAirConditionState(){
        return airConditionRepository.findLastAirConditionState();
    }

    public void deleteRowsOverLimit(){
        if(this.rowsCounter >= rowsLimit) {
            airConditionRepository.deleteFirstAirCondition();
            this.rowsCounter--;
        }
    }
}
