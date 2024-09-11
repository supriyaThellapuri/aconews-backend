//package com.news.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class NewsService {
//
//    @Value("${gnews.api.key}")
//    private String apiKey;
//
//    private final String BASE_URL = "https://gnews.io/api/v4/search";
//
//    public String getNews(String query, int page) {
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            String url = BASE_URL + "?q=" + query + "&token=" + apiKey + "&page=" + page + "&lang=en";
//            return restTemplate.getForObject(url, String.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error occurred while fetching news.";
//        }
//    }
//
//}

package com.news.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsService {

    @Value("${gnews.api.key}")
    private String apiKey;

    private final String BASE_URL = "https://gnews.io/api/v4/search";

    public String getNews(String query, int page) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + "?q=" + query + "&token=" + apiKey + "&page=" + page + "&lang=en";

        try {
            return restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException e) {
            System.err.println("HTTP Client Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            System.err.println("HTTP Server Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("Error occurred while fetching news: " + e.getMessage());
        }

        return "Error occurred while fetching news.";
    }
}
