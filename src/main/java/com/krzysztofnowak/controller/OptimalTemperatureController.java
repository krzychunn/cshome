package com.krzysztofnowak.controller;

import com.krzysztofnowak.entity.OptimalTemperature;
import com.krzysztofnowak.service.OptimalTemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Krzychu on 20.01.2017.
 */

@Controller
@RequestMapping("/optimalTemperature/")
public class OptimalTemperatureController {

    private OptimalTemperatureService optimalTemperatureService;

    @Autowired
    public OptimalTemperatureController(OptimalTemperatureService service) {this.optimalTemperatureService = service;};

    @RequestMapping(method = RequestMethod.POST, path ="add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postMinimumDistance(@RequestBody OptimalTemperature optimalTemperature){
        optimalTemperature.setTimeStamp(new Date());
        optimalTemperatureService.save(optimalTemperature);
    }

    @RequestMapping(method = RequestMethod.GET, path="getAll")
    @ResponseBody
    public List<OptimalTemperature> getOptimalTemperatures() {
        List<OptimalTemperature> optimalTemperatures = optimalTemperatureService.findAll();
        return optimalTemperatures;
    }

    @RequestMapping(method = RequestMethod.GET, path="getLast")
    @ResponseBody
    public OptimalTemperature getLastOptimalTemperature() {
        OptimalTemperature optimalTemperature = optimalTemperatureService.findLastOptimalTemperature();
        return optimalTemperature;
    }
}
