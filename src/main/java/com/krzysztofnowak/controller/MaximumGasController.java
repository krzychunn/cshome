package com.krzysztofnowak.controller;

import com.krzysztofnowak.entity.MaximumGas;
import com.krzysztofnowak.service.MaximumGasService;
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
@RequestMapping("/maximumGas/")
public class MaximumGasController {

    private MaximumGasService maximumGasService;

    @Autowired
    public MaximumGasController(MaximumGasService service) {this.maximumGasService =service;};

    @RequestMapping(method = RequestMethod.POST, path ="add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postMaximumGas(@RequestBody MaximumGas maximumGas){
        maximumGas.setTimeStamp(new Date());
        maximumGasService.save(maximumGas);
    }

    @RequestMapping(method = RequestMethod.GET, path="getAll")
    @ResponseBody
    public List<MaximumGas> getMaximumGases() {
        List<MaximumGas> maximumGases = maximumGasService.findAll();
        return maximumGases;
    }

    @RequestMapping(method = RequestMethod.GET, path="getLast")
    @ResponseBody
    public MaximumGas getLastMaximumGas() {
        MaximumGas maximumGas = maximumGasService.findLastMaximumGas();
        return maximumGas;
    }
}
