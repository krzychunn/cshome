package com.krzysztofnowak.controller;

import com.krzysztofnowak.entity.Light;
import com.krzysztofnowak.service.LightService;
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
@RequestMapping("/light/")
public class LightController {

    private LightService lightService;

    @Autowired
    public LightController(LightService service) {this.lightService = service;};

    @RequestMapping(method = RequestMethod.POST, path ="add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postAirCondition(@RequestBody Light light){
        light.setTimeStamp(new Date());
        lightService.save(light);
    }

    @RequestMapping(method = RequestMethod.GET, path="getAll")
    @ResponseBody
    public List<Light> getLights() {
        List<Light> lights = lightService.findAll();
        return lights;
    }

    @RequestMapping(method = RequestMethod.GET, path="getLast")
    @ResponseBody
    public Light getLastLight() {
        Light light = lightService.findLastBuzzerState();
        return light;
    }
}
