package com.krzysztofnowak.service;

import com.krzysztofnowak.entity.*;
import com.krzysztofnowak.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Krzychu on 29.12.2016.
 */
@Component
public class MeasurementService {

    private MeasurementRepository measurementRepository;
    private OptimalTemperatureRepository optimalTemperatureRepository;
    private BuzzerRepository buzzerRepository;
    private HeatingRepository heatingRepository;

    private int rowsCounter;
    //Page<Integer> page;
    private int rowsLimit = 4320;
    //PageRequest pageRequest = new PageRequest(0, rowsLimit);

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
        this.rowsCounter = countAll();
        deleteRowsOverLimit();
    }

    public int countAll(){
        return (int) measurementRepository.count();
    }

    public List<Measurement> findAll(){
       return this.measurementRepository.findAll();
    }

    public void save(Measurement measurement){
        measurementRepository.save(measurement);
        this.rowsCounter++;
        deleteRowsOverLimit();
    }

    public Measurement findLastMeasurement(){
        return measurementRepository.findLastMeasurement();
    }

    public void deleteRowsOverLimit(){
        if(this.rowsCounter >= rowsLimit) {
            measurementRepository.deleteFirstMeasurement();
            this.rowsCounter--;
        }
    }

}
