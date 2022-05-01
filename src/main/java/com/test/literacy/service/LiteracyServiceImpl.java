package com.test.literacy.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LiteracyServiceImpl implements LiteracyService {

    private final List<String> results = new ArrayList<>();

    @Override
    public List<String> countLiteracy(List<String> texts) {

        texts.forEach(this::countLetters);

        return results;
    }

    private void countLetters(String text){
        HashMap<Character, Integer> counting = new HashMap<>();

        for (String word : text.replaceAll("[(?![@',&])\\p{Punct}]", "").split(" ")) {
            counting.put(word.charAt(0),
                    counting.get(word.charAt(0)) == null ? 1 : counting.get(word.charAt(0))+1);
        }
        results.add(
                Collections.max(counting.values()) * 100 / counting.size() + "%"
        );



    }
}
