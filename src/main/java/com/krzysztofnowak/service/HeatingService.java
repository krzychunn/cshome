package com.krzysztofnowak.service;

import com.krzysztofnowak.entity.Heating;
import com.krzysztofnowak.repository.HeatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzychu on 30.12.2016.
 */

@Component
public class HeatingService {

    private HeatingRepository heatingRepository;

    private int rowsCounter;
    private int rowsLimit = 100;

    @Autowired
    public HeatingService(HeatingRepository repository) {
        this.heatingRepository = repository;
        this.rowsCounter = countAll();
        deleteRowsOverLimit();
    }

    public int countAll(){
        return (int) heatingRepository.count();
    }

    public List<Heating> findAll(){
        return this.heatingRepository.findAll();
    }

    public void save(Heating heating){
        heatingRepository.save(heating);
        this.rowsCounter++;
        deleteRowsOverLimit();
    }

    public Heating findLastHeatingState(){
        return heatingRepository.findLastHeatingState();
    }

    public void deleteRowsOverLimit(){
        if(this.rowsCounter >= rowsLimit) {
            heatingRepository.deleteFirstHeating();
            this.rowsCounter--;
        }
    }
}
