package com.krzysztofnowak.controller;

import com.krzysztofnowak.entity.AirCondition;
import com.krzysztofnowak.service.AirConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Krzychu on 09.02.2017.
 */

@Controller
@RequestMapping("/airCondition/")
public class AirConditionController {

    private AirConditionService airConditionService;

    @Autowired
    public AirConditionController(AirConditionService service) {this.airConditionService = service;};

    @RequestMapping(method = RequestMethod.POST, path ="add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postAirCondition(@RequestBody AirCondition airCondition){
        airCondition.setTimeStamp(new Date());
        airConditionService.save(airCondition);
    }

    @RequestMapping(method = RequestMethod.GET, path="getAll")
    @ResponseBody
    public List<AirCondition> getAirConditions() {
        List<AirCondition> airConditions = airConditionService.findAll();
        return airConditions;
    }

    @RequestMapping(method = RequestMethod.GET, path="getLast")
    @ResponseBody
    public AirCondition getLastAirCondition() {
        AirCondition airCondition = airConditionService.findLastAirConditionState();
        return airCondition;
    }
}
