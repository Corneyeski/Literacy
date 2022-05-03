package com.test.literacy.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LiteracyServiceImpl implements LiteracyService {

    @Override
    public List<String> countLiteracy(List<String> texts) {

        // % of literacy in each text
        List<String> results = new ArrayList<>();

        //Send each text to the function countLetters
        texts.forEach(text -> countLetters(results, text));

        return results;
    }

    @Override
    public boolean splitWorkers(List<Integer> jobs) {

        if (jobs.size() < 5)
            return false;

        boolean loopBreaker = false;

        int firstHalf = 1;
        int secondHalf = 3;

        do {

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
            else if(secondHalf < jobs.size() -2)
                    secondHalf++;
            else if(firstHalf < jobs.size() -3) {
                firstHalf++;
                secondHalf = firstHalf+2;
            } else loopBreaker = true;

        }while (!loopBreaker);

        return false;
    }


    private List<String> countLetters(List<String> results, String text) {
        HashMap<Character, Integer> counting = new HashMap<>();

        for (String word : text.replaceAll("[(?![@',&])\\p{Punct}]", "").split(" ")) {
            counting.put(word.charAt(0),
                    counting.get(word.charAt(0)) == null ? 1 : counting.get(word.charAt(0)) + 1);
        }
        results.add(
                Collections.max(counting.values()) * 100 / counting.size() + "%"
        );

        return results;
    }
}
