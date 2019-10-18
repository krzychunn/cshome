package com.krzysztofnowak.service;

import com.krzysztofnowak.entity.MaximumGas;
import com.krzysztofnowak.repository.MaximumGasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzychu on 31.12.2016.
 */
@Component
public class MaximumGasService {

    private MaximumGasRepository maximumGasRepository;

    private int rowsCounter;
    private int rowsLimit = 100;

    @Autowired
    public MaximumGasService(MaximumGasRepository repository) {
        this.maximumGasRepository = repository;
        this.rowsCounter = countAll();
        deleteRowsOverLimit();
    }

    public int countAll(){
        return (int) maximumGasRepository.count();
    }

    public List<MaximumGas> findAll(){
        return this.maximumGasRepository.findAll();
    }

    public void save(MaximumGas maximumGas){
        maximumGasRepository.save(maximumGas);
        this.rowsCounter++;
        deleteRowsOverLimit();
    }

    public MaximumGas findLastMaximumGas(){
        return maximumGasRepository.findLastMaximumGas();
    }

    public void deleteRowsOverLimit(){
        if(this.rowsCounter >= rowsLimit) {
            maximumGasRepository.deleteFirstMaximumGas();
            this.rowsCounter--;
        }
    }
}
