package com.krzysztofnowak.service;

import com.krzysztofnowak.entity.Humidifier;
import com.krzysztofnowak.repository.HumidifierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzychu on 02.03.2017.
 */
@Component
public class HumidifierService {

    private HumidifierRepository humidifierRepository;

    private int rowsCounter;
    private int rowsLimit = 100;

    @Autowired
    public HumidifierService(HumidifierRepository repository) {
        this.humidifierRepository = repository;
        this.rowsCounter = countAll();
        deleteRowsOverLimit();
    }

    public int countAll(){
        return (int) humidifierRepository.count();
    }

    public List<Humidifier> findAll(){
        return this.humidifierRepository.findAll();
    }

    public void save(Humidifier humidifier){
        humidifierRepository.save(humidifier);
        this.rowsCounter++;
        deleteRowsOverLimit();
    }

    public Humidifier findLastHumidifierState(){
        return humidifierRepository.findLastHumidifierState();
    }

    public void deleteRowsOverLimit(){
        if(this.rowsCounter >= rowsLimit) {
            humidifierRepository.deleteFirstHumidifier();
            this.rowsCounter--;
        }
    }
}
