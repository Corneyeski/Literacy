package com.test.literacy.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LiteracyService {

    List<Integer> countLiteracy(List<String> texts);

}
