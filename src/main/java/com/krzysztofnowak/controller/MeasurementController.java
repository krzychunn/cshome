package com.krzysztofnowak.controller;

import com.krzysztofnowak.entity.Measurement;
import com.krzysztofnowak.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.krzysztofnowak.repository.MeasurementRepository;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Krzychu on 29.12.2016.
 */

@Controller
@RequestMapping("/measurements/")
public class MeasurementController {

    private MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService service) {
        this.measurementService = service;
    }

    @RequestMapping(method = RequestMethod.POST, path ="add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postMeasurements(@RequestBody Measurement measurement){
        measurement.setTimeStamp(new Date());
        measurementService.save(measurement);
    }

    @RequestMapping(method = RequestMethod.GET, path="getAll")
    @ResponseBody
    public List<Measurement> getMeasurements() {
        List<Measurement> measurements = measurementService.findAll();
        return measurements;
    }

    @RequestMapping(method = RequestMethod.GET, path="getLast")
    @ResponseBody
    public Measurement getLastMeasurement() {
        Measurement measurement = measurementService.findLastMeasurement();
        return measurement;
    }

    @RequestMapping(method = RequestMethod.GET, path="getLastLightIntensity")
    @ResponseBody
    public String getLastMeasurementLightIntensity() {
        Measurement measurement = measurementService.findLastMeasurement();
        return measurement.getLightIntensity();
    }

    @RequestMapping(method = RequestMethod.GET, path="getLastGasLevel")
    @ResponseBody
    public String getLastMeasurementGasDensity() {
        Measurement measurement = measurementService.findLastMeasurement();
        return measurement.getGasDensity();
    }

    @RequestMapping(method = RequestMethod.GET, path="getLastTemperature")
    @ResponseBody
    public String getLastMeasurementTemperature() {
        Measurement measurement = measurementService.findLastMeasurement();
        return measurement.getTemperature();
    }

    @RequestMapping(method = RequestMethod.GET, path="getLastHumidity")
    @ResponseBody
    public String getLastMeasurementHumidity() {
        Measurement measurement = measurementService.findLastMeasurement();
        return measurement.getHumidity();
    }

    @RequestMapping(method = RequestMethod.GET, path="getLastTimestamp")
    @ResponseBody
    public Date getLastMeasurementTimestamp() {
        Measurement measurement = measurementService.findLastMeasurement();
        return measurement.getTimeStamp();
    }
}
