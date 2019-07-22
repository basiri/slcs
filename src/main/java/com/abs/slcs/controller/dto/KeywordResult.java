package com.abs.slcs.controller.dto;

public class KeywordResult {
    private String keyword;
    private int score;

    public KeywordResult(String keyword, int score) {
        this.keyword = keyword;
        this.score = score;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getScore() {
        return score;
    }
}
