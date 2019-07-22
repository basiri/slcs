package com.abs.slcs.service;

import com.abs.slcs.controller.dto.KeywordResult;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class KeywordResultCalculatorServiceImplTest {

    @Test
    public void calculateScore() {
        KeywordScoreCalculatorServiceImpl service= new KeywordScoreCalculatorServiceImpl();
        KeywordResult r=null;
        //String keyword = "galaxy";
        String keyword = "samsung galaxy s9 case wallet for women cute";
        try {
            r =service.calculateScore(keyword);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assertEquals(10,r.getScore());

        keyword = "samsung galaxy s9";
        try {
            r =service.calculateScore(keyword);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assertEquals(100,r.getScore());

        keyword = "saipa";
        try {
            r =service.calculateScore(keyword);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assertEquals(0,r.getScore());

    }
}