package com.test.literacy.rest;


import com.test.literacy.service.LiteracyService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.test.literacy.utils.Constants.BALANCER_PATH;
import static com.test.literacy.utils.Constants.LITERACY_PATH;

@Validated
@RestController
@RequestMapping(LITERACY_PATH)
public class LiteracyController {

    private final LiteracyService literacyService;

    @Autowired
    public LiteracyController(LiteracyService literacyService) {
        this.literacyService = literacyService;
    }

    @GetMapping
    public List<String> literacyCount(@RequestBody @NotEmpty(message = "List of texts cannot be empty") List<String> texts){
        return literacyService.countLiteracy(texts);
    }

    @GetMapping(BALANCER_PATH)
    public boolean workersBalancer(@RequestBody List<Integer> jobs){
        return literacyService.splitWorkers(jobs);
    }


}
