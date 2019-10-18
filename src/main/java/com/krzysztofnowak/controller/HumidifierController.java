package com.krzysztofnowak.controller;

import com.krzysztofnowak.entity.Humidifier;
import com.krzysztofnowak.service.HumidifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Krzychu on 02.03.2017.
 */
@Controller
@RequestMapping("/humidifier/")
public class HumidifierController {

    private HumidifierService humidifierService;

    @Autowired
    public HumidifierController(HumidifierService service) {this.humidifierService = service;};

    @RequestMapping(method = RequestMethod.POST, path ="add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postHeating(@RequestBody Humidifier humidifier){
        humidifier.setTimeStamp(new Date());
        humidifierService.save(humidifier);
    }

    @RequestMapping(method = RequestMethod.GET, path="getAll")
    @ResponseBody
    public List<Humidifier> getHumidifiers() {
        List<Humidifier> humidifiers = humidifierService.findAll();
        return humidifiers;
    }

    @RequestMapping(method = RequestMethod.GET, path="getLast")
    @ResponseBody
    public Humidifier getLastHumidifier() {
        Humidifier humidifier = humidifierService.findLastHumidifierState();
        return humidifier;
    }
}
