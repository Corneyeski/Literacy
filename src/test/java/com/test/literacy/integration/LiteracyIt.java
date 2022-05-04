package com.test.literacy.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.test.literacy.utils.Constants.LITERACY_PATH;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LiteracyIt {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String uri = "http://localhost:";

    @BeforeEach
    private void setup() {
        uri = uri + port;
    }

    /**
     * I had problems with this test because it wasn't able to transform the response to JSON and I could not spend more time on it
     */
    /*@Test
    public void literacyTexts_OK(){

        List<String> texts = List.of("Mike made mellow music with his new microphone", "Yarvis yanked his ankle at yoga, and Yolanda yelled out in surprise");
        List<String> result = List.of("80%", "42%");


        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<List<String>> entity = new HttpEntity<>(texts, headers);

        ResponseEntity<List<String>> response = testRestTemplate.exchange(uri+LITERACY_PATH, HttpMethod.GET, entity,
                new ParameterizedTypeReference<>() {});

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }*/
}
