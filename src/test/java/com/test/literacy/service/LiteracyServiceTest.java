package com.test.literacy.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class LiteracyServiceTest {

    @Autowired
    private LiteracyService literacyService;

    @Test
    public void countLiteracy_OK() {
        List<String> texts = List.of("Mike made mellow music with his new microphone", "Yarvis yanked his ankle at yoga, and Yolanda yelled out in surprise");

        List<String> result = literacyService.countLiteracy(texts);

        assertEquals(2, result.size());
        assertEquals("80%", result.get(0));
        assertEquals("42%", result.get(1));
    }

    @Test
    public void workersBalancer_isTrue_OK() {
        List<Integer> jobs = List.of(1,3,4,2,2,2,1,1,2);
        assertTrue(literacyService.splitWorkers(jobs));
    }

    @Test
    public void workersBalancer_isFalse_OK() {
        List<Integer> jobs = List.of(1,1,1,1,1,1);
        assertFalse(literacyService.splitWorkers(jobs));
    }
}
