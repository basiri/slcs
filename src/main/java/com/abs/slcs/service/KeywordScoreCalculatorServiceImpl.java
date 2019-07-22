package com.abs.slcs.service;

import com.abs.slcs.controller.dto.KeywordResult;
import com.abs.slcs.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

@Service
public class KeywordScoreCalculatorServiceImpl implements KeywordScoreCalculatorService{
    private static final Logger logger = LoggerFactory.getLogger(KeywordScoreCalculatorServiceImpl.class);

    public KeywordResult calculateScore(String keyword) throws URISyntaxException {
        //transform keyword to url parameter
        String urlyfiedKeyword = keyword.toLowerCase().trim();
        if(urlyfiedKeyword.contains(" ")) {
            Utility utility = new Utility();
            urlyfiedKeyword = utility.urlify(urlyfiedKeyword.toCharArray());
        }
        //calling Amazon Api
        final String baseUrl =
                "http://completion.amazon.com/search/complete?search-alias=aps&client=amazon-search-ui&mkt=1&q="
                + urlyfiedKeyword;

        URI uri = new URI(baseUrl);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(uri,String.class);
        // Commented to be faster
        //logger.info("response: "+response);

        String alphaAndDigits = response.replaceAll("[^\\p{Alpha}\\p{Digit},]+"," ");
        String[] results = alphaAndDigits.split(",");
        //calculating the score
        int scoreExact = (int) Arrays.stream(results).filter(s -> s.contains(keyword.toLowerCase()+" ")).count()-1;
        scoreExact = scoreExact<1?0:scoreExact*10;

        return new KeywordResult(keyword,scoreExact);

    }

}
