package com.test.literacy.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LiteracyServiceImpl implements LiteracyService {

    @Override
    public List<Integer> countLiteracy(List<String> texts) {

        HashMap<Character, Integer> counting = new HashMap<>();

        texts.forEach(System.out::println);

        texts.forEach(this::countLetters);
        /*{
            //String[] s = t.replaceAll("[-+.^:,]", "").split(" ");

           /* for (String word : t.replaceAll("[-+.^:,]", "").split(" ")) {
                counting.put(word.charAt(0),
                        counting.get(word.charAt(0)) == null ? 1 : counting.get(word.charAt(0))+1);
            }
        });*/


        //float percent = (n * 100.0f) / v;

        return null;
    }

    private void countLetters(String text){
        HashMap<Character, Integer> counting = new HashMap<>();

        for (String word : text.replaceAll("[-+.^:,]", "").split(" ")) {
            counting.put(word.charAt(0),
                    counting.get(word.charAt(0)) == null ? 1 : counting.get(word.charAt(0))+1);
        }


    }
}
