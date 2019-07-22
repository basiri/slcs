package com.abs.slcs.service;

import com.abs.slcs.controller.dto.KeywordResult;

import java.net.URISyntaxException;

public interface KeywordScoreCalculatorService {

    KeywordResult calculateScore(String keyword)throws URISyntaxException;
}
