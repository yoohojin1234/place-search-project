package com.hj.placesearchproject.service;

import com.hj.placesearchproject.mapper.SearchMapper;
import com.hj.placesearchproject.model.SearchLog;
import com.hj.placesearchproject.properties.KakaoProperty;
import com.hj.placesearchproject.properties.NaverProperty;
import com.hj.placesearchproject.util.KakaoRestClient;
import com.hj.placesearchproject.util.NaverRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private KakaoProperty kakaoProperty;

    @Autowired
    private NaverProperty naverProperty;

    @Autowired
    private KakaoRestClient kakaoRestClient;

    @Autowired
    private NaverRestClient naverRestClient;

    @Autowired
    private SearchMapper searchMapper;


    public <T> T kakaoSearch(String jsonBody, Class<T> responseType, String query, int size) {
        String url = String.format("https://%s?query=%s&size=%d", kakaoProperty.getUrl(), query, size);
        kakaoRestClient.setApiKey(kakaoProperty.getApiKey());
        return kakaoRestClient.call(HttpMethod.POST, url, jsonBody, responseType);
    }

    public <T> T naverSearch(String jsonBody, Class<T> responseType, String query) {
        String url = String.format("https://%s?query=%s&display=5", naverProperty.getUrl(), query);
        naverRestClient.setClientId(naverProperty.getClientId());
        naverRestClient.setClientSecret(naverProperty.getClientSecret());
        return naverRestClient.call(HttpMethod.GET, url, jsonBody, responseType);
    }

    public void insertSearchLog(SearchLog searchLog) {
        searchMapper.insertSearchLog(searchLog);
    }

    public List<SearchLog> findTop10() {
        return searchMapper.findTop10();
    }
}
