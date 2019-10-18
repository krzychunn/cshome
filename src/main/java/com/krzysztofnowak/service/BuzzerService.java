package com.krzysztofnowak.service;

import com.krzysztofnowak.entity.Buzzer;
import com.krzysztofnowak.repository.BuzzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzychu on 30.12.2016.
 */

@Component
public class BuzzerService {

    private BuzzerRepository buzzerRepository;

    private int rowsCounter;
    private int rowsLimit = 100;

    @Autowired
    public BuzzerService(BuzzerRepository repository) {
        this.buzzerRepository = repository;
        this.rowsCounter = countAll();
        deleteRowsOverLimit();
    }

    public int countAll(){
        return (int) buzzerRepository.count();
    }

    public List<Buzzer> findAll(){
        return this.buzzerRepository.findAll();
    }

    public void save(Buzzer buzzer){
        buzzerRepository.save(buzzer);
        this.rowsCounter++;
        deleteRowsOverLimit();
    }

    public Buzzer findLastBuzzerState(){
        return buzzerRepository.findLastBuzzerState();
    }

    public void deleteRowsOverLimit(){
        if(this.rowsCounter >= rowsLimit) {
            buzzerRepository.deleteFirstBuzzer();
            this.rowsCounter--;
        }
    }
}
