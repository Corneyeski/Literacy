package com.test.literacy.rest;


import com.test.literacy.service.LiteracyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/literacy")
public class LiteracyController {

    private final LiteracyService literacyService;

    @Autowired
    public LiteracyController(LiteracyService literacyService) {
        this.literacyService = literacyService;
    }

    @GetMapping
    public void literacyCount(@RequestBody List<String> texts){
        literacyService.countLiteracy(texts);
    }


}
