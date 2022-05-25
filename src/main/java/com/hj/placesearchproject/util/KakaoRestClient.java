package com.hj.placesearchproject.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Component
public class KakaoRestClient {
    private HttpHeaders headers;
    private RestTemplate restTemplate;
    private HttpEntity<String> entity;

    public KakaoRestClient() {
        this.headers = getHeaders();
        this.restTemplate = new RestTemplate();
        this.restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

    public <T> T call(HttpMethod httpMethod, String url, String body, Class<T> responseType) {
        entity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, entity, String.class);

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        return gson.fromJson(response.getBody(), responseType);
    }

    public void setApiKey(String apiKey) {
        this.headers.set("Authorization", "KakaoAK " + apiKey);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        return httpHeaders;
    }
}
