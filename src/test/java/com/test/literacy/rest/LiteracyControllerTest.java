package com.test.literacy.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.literacy.service.LiteracyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static com.test.literacy.utils.Constants.BALANCER_PATH;
import static com.test.literacy.utils.Constants.LITERACY_PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LiteracyController.class)
public class LiteracyControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LiteracyService service;

    @Test
    public void sendTextsAndVerify_OK() throws Exception {

        List<String> texts = List.of("Mike made mellow music with his new microphone", "Yarvis yanked his ankle at yoga, and Yolanda yelled out in surprise");
        List<String> result = List.of("80%", "42%");

        when(service.countLiteracy(texts)).thenReturn(result);

        MvcResult mvcResult = this.mockMvc.perform(get(LITERACY_PATH)
                .contentType("application/json")
                        .content(objectMapper.writeValueAsString(texts)))
                .andExpect(status().isOk()).andReturn();


        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(result));
    }

    @Test
    public void sendTextsAndVerify_BAD_REQUEST() throws Exception {

        List<String> texts = List.of();

        this.mockMvc.perform(get(LITERACY_PATH)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(texts)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void workersBalancer_OK() throws Exception {

        List<Integer> jobs = List.of(1,3,4,2,2,2,1,1,2);

        when(service.splitWorkers(jobs)).thenReturn(true);

        MvcResult mvcResult = this.mockMvc.perform(get(LITERACY_PATH + BALANCER_PATH)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(jobs)))
                .andExpect(status().isOk()).andReturn();


        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace("true");
    }

}
