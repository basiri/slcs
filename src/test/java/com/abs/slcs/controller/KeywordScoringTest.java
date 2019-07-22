package com.abs.slcs.controller;

import com.abs.slcs.controller.dto.KeywordResult;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KeywordScoringTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;


    @Test
    public void isRunning() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/test";
        URI uri = new URI(baseUrl);
        ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);
        //Verify bad request and missing header
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertTrue(result.getBody().contains("OK!!!"));
    }

    @Test
    public void keywordScore() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/keyword-calculate";
        //URI uri = new URI(baseUrl);
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("keyword", "apple watch women").build().toUri();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>( headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request,String.class);

        //Verify bad request and missing header
        assertEquals(200, result.getStatusCodeValue());
        Gson gson = new Gson();
        KeywordResult keywordResult =gson.fromJson(result.getBody(),KeywordResult.class);


        assertEquals(30, keywordResult.getScore());
    }
}