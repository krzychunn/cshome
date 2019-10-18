package com.krzysztofnowak.controller;

import com.krzysztofnowak.entity.Buzzer;
import com.krzysztofnowak.service.BuzzerService;
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
@RequestMapping("/buzzer/")
public class BuzzerController {

    private BuzzerService buzzerService;

    @Autowired
    public BuzzerController(BuzzerService service) {this.buzzerService = service;};

    @RequestMapping(method = RequestMethod.POST, path ="add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void postBuzzer(@RequestBody Buzzer buzzer){
        buzzer.setTimeStamp(new Date());
        buzzerService.save(buzzer);
    }

    @RequestMapping(method = RequestMethod.GET, path="getAll")
    @ResponseBody
    public List<Buzzer> getBuzzers() {
        List<Buzzer> buzzers = buzzerService.findAll();
        return buzzers;
    }

    @RequestMapping(method = RequestMethod.GET, path="getLast")
    @ResponseBody
    public Buzzer getLastBuzzer() {
        Buzzer buzzer = buzzerService.findLastBuzzerState();
        return buzzer;
    }

}
