package com.krzysztofnowak.controller;

import com.krzysztofnowak.entity.MinimumLight;
import com.krzysztofnowak.service.MinimumLightService;
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
@RequestMapping("/minimumLight/")
public class MinimumLightController {

    private MinimumLightService minimumLightService;

    @Autowired
    public MinimumLightController(MinimumLightService service) {this.minimumLightService = service;};

    @RequestMapping(method = RequestMethod.POST, path ="add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postMinimumDistance(@RequestBody MinimumLight minimumLight){
        minimumLight.setTimeStamp(new Date());
        minimumLightService.save(minimumLight);
    }

    @RequestMapping(method = RequestMethod.GET, path="getAll")
    @ResponseBody
    public List<MinimumLight> getOptimalTemperatures() {
        List<MinimumLight> minimumLights = minimumLightService.findAll();
        return minimumLights;
    }

    @RequestMapping(method = RequestMethod.GET, path="getLast")
    @ResponseBody
    public MinimumLight getLastMinimumDistance() {
        MinimumLight optimalTemperature = minimumLightService.findLastMinimumLight();
        return optimalTemperature;
    }
}
