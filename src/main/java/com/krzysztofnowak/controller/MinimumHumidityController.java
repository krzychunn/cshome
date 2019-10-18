package com.krzysztofnowak.controller;

import com.krzysztofnowak.entity.MinimumHumidity;
import com.krzysztofnowak.service.MinimumHumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Krzychu on 28.02.2017.
 */

@Controller
@RequestMapping("/minimumHumidity/")
public class MinimumHumidityController {

    private MinimumHumidityService minimumHumidityService;

    @Autowired
    public MinimumHumidityController(MinimumHumidityService service) {this.minimumHumidityService=service;};

    @RequestMapping(method = RequestMethod.POST, path ="add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postMinimumGas(@RequestBody MinimumHumidity minimumHumidity){
        minimumHumidity.setTimeStamp(new Date());
        minimumHumidityService.save(minimumHumidity);
    }

    @RequestMapping(method = RequestMethod.GET, path="getAll")
    @ResponseBody
    public List<MinimumHumidity> getMinimumHumidities() {
        List<MinimumHumidity> minimumHumidities = minimumHumidityService.findAll();
        return minimumHumidities;
    }

    @RequestMapping(method = RequestMethod.GET, path="getLast")
    @ResponseBody
    public MinimumHumidity getLastMinimumHumidity() {
        MinimumHumidity minimumHumidity = minimumHumidityService.findLastMinimumHumidity();
        return minimumHumidity;
    }
}
