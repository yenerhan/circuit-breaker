package com.yener.circuitbreaker.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

public class GenericRestClient<T, V> {

    private RestTemplate restTemplate = new RestTemplate();

    public V execute(String url, HttpMethod httpMethod, T data, ResponseErrorHandler errorHandler,
                     Class<V> genericClass) throws ResourceAccessException, Exception {

        restTemplate.setErrorHandler(errorHandler);
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<T> entity = new HttpEntity<T>(data, headers);
        ResponseEntity<V> response = restTemplate.exchange(url, httpMethod,
                entity, genericClass);
        return response.getBody();
    }

}
