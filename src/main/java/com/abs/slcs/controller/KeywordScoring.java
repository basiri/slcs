package com.abs.slcs.controller;

import com.abs.slcs.controller.dto.KeywordResult;
import com.abs.slcs.service.KeywordScoreCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URISyntaxException;

/**
 * this controller is responsible for getting the keyword
 * and invoking the score calculator service
 */
@RestController
public class KeywordScoring {
    // keyword maximum limit
    @Value("${MAX_STRING_SIZE}")
    int maxStringSize;

    private static final Logger logger = LoggerFactory.getLogger(KeywordScoring.class);


    private KeywordScoreCalculatorService scoreCalculatorService;

    public KeywordScoring(KeywordScoreCalculatorService scoreCalculatorService) {
        this.scoreCalculatorService = scoreCalculatorService;
    }

    @GetMapping("/test")
    public @ResponseBody
    String isRunning() {
        return "OK!!!";
    }

    @PostMapping(path = "/keyword-calculate", produces = "application/json")
    public KeywordResult keywordCalculator(@RequestParam("keyword") String keyword) {
        // to set a limitation for the string
        if (keyword.length()>maxStringSize) throw new
                ResponseStatusException(HttpStatus.BAD_REQUEST, "The input keyword is too long.");

        try {
            return scoreCalculatorService.calculateScore(keyword);
        } catch (URISyntaxException e) {
            //e.printStackTrace();
            logger.error("The input keyword is not valid.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The input keyword is not valid.");
        }
    }


}
