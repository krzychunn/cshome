package com.krzysztofnowak.controller;

import com.krzysztofnowak.entity.Heating;
import com.krzysztofnowak.service.HeatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Krzychu on 13.01.2017.
 */

@Controller
@RequestMapping("/heating/")
public class HeatingController {

    private HeatingService heatingService;

    @Autowired
    public HeatingController(HeatingService service) {this.heatingService = service;};

    @RequestMapping(method = RequestMethod.POST, path ="add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postHeating(@RequestBody Heating heating){
        heating.setTimeStamp(new Date());
        heatingService.save(heating);
    }

    @RequestMapping(method = RequestMethod.GET, path="getAll")
    @ResponseBody
    public List<Heating> getHeatings() {
        List<Heating> heatings = heatingService.findAll();
        return heatings;
    }

    @RequestMapping(method = RequestMethod.GET, path="getLast")
    @ResponseBody
    public Heating getLastHeating() {
        Heating heating = heatingService.findLastHeatingState();
        return heating;
    }
}
