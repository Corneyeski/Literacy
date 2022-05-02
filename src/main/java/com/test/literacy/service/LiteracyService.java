package com.test.literacy.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LiteracyService {

    List<String> countLiteracy(List<String> texts);

    boolean splitWorkers(List<Integer> jobs);

}
