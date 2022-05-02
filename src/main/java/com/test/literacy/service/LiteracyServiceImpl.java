package com.test.literacy.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LiteracyServiceImpl implements LiteracyService {

    // % of literacy in each text
    private final List<String> results = new ArrayList<>();

    @Override
    public List<String> countLiteracy(List<String> texts) {

        //Send each text to the function countLetters
        texts.forEach(this::countLetters);

        return results;
    }

    @Override
    public boolean splitWorkers(List<Integer> jobs) {

        if (jobs.size() < 5)
            return false;

        int firstHalf = (jobs.size() / 2) / 2;
        int secondHalf = jobs.size() % 2 == 0 ? (jobs.size() / 4) + (jobs.size() / 2) : ((jobs.size() / 4)) + (jobs.size() / 2) - 1;

        for (int job : jobs) {

            int result = 0;
            int result2 = 0;
            int result3 = 0;

            //Sum first half
            for (int j = 0; j < firstHalf; j++)
                result = result + jobs.get(j);


            //Sum second half
            for (int j = firstHalf + 1; j < secondHalf; j++)
                result2 = result2 + jobs.get(j);


            //Sum third half
            for (int j = secondHalf + 1; j < jobs.size(); j++)
                result3 = result3 + jobs.get(j);


            //Check if workers have a balanced work
            if (result == result2 && result == result3)
                return true;

            //if we get to the first or last index of the jobs list, it's not possible to split jobs equally
            if(firstHalf > 0)
                firstHalf--;
            else return false;

            if(secondHalf < jobs.size())
                secondHalf++;
            else return false;
        }
        return false;
    }


    private void countLetters(String text) {
        HashMap<Character, Integer> counting = new HashMap<>();

        for (String word : text.replaceAll("[(?![@',&])\\p{Punct}]", "").split(" ")) {
            counting.put(word.charAt(0),
                    counting.get(word.charAt(0)) == null ? 1 : counting.get(word.charAt(0)) + 1);
        }
        results.add(
                Collections.max(counting.values()) * 100 / counting.size() + "%"
        );
    }
}
