package com.hj.placesearchproject.controller;

import com.hj.placesearchproject.model.PlaceApiResponse;
import com.hj.placesearchproject.model.SearchApiResponse;
import com.hj.placesearchproject.model.SearchLog;
import com.hj.placesearchproject.model.kakao.KakaoDocument;
import com.hj.placesearchproject.model.kakao.KakaoSearchPlace;
import com.hj.placesearchproject.model.PlaceParam;
import com.hj.placesearchproject.model.naver.NaverItem;
import com.hj.placesearchproject.model.naver.NaverSearchPlace;
import com.hj.placesearchproject.properties.KakaoProperty;
import com.hj.placesearchproject.service.SearchService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private KakaoProperty kakaoProperty;

    @GetMapping(value = "/place", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> place(HttpServletRequest request) throws UnsupportedEncodingException {

        String q = URLDecoder.decode(request.getParameter("q"), "UTF-8");
        SearchLog searchLog = new SearchLog();
        searchLog.setSearchPlace(q);
        searchService.insertSearchLog(searchLog);

        JSONObject requestParam = new JSONObject();
        int maxSize = 10;

        NaverSearchPlace naverSearchPlace = searchService.naverSearch(requestParam.toString(), NaverSearchPlace.class, q);
        List<String> naverPlaceList = new ArrayList<>();
        List<String> naverPlaceNonBlankList = new ArrayList<>();
        for(NaverItem item : naverSearchPlace.getItems()) {
            String deleteHtmlTagTitle = item.getTitle().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","");
            naverPlaceList.add(deleteHtmlTagTitle);
            naverPlaceNonBlankList.add(deleteHtmlTagTitle.replaceAll("\\s", ""));
        }

        int kakaoSize = maxSize - naverSearchPlace.getItems().size();
        KakaoSearchPlace kakaoSearchPlace = searchService.kakaoSearch(requestParam.toString(), KakaoSearchPlace.class, q, kakaoSize);
        List<String> kakaoPlaceList = new ArrayList<>();
        for(KakaoDocument document : kakaoSearchPlace.getDocuments()) {
            kakaoPlaceList.add(document.getPlaceName().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>",""));
        }

        List<String> resultPlaceList = new ArrayList<>();

        for(int i = 0; i < kakaoPlaceList.size(); i++) {
            if(naverPlaceNonBlankList.contains(kakaoPlaceList.get(i).replaceAll("\\s", ""))) {
                resultPlaceList.add(kakaoPlaceList.get(i));
                naverPlaceList.remove(kakaoPlaceList.get(i));
                kakaoPlaceList.remove(i);
                i--;
            }
        }

        resultPlaceList.addAll(kakaoPlaceList);
        resultPlaceList.addAll(naverPlaceList);
        PlaceApiResponse placeApiResponse = new PlaceApiResponse();
        placeApiResponse.setPlacesAsList(resultPlaceList);


        return new ResponseEntity<>(placeApiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/searchTop10",  produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> searchTop10() {
        List<SearchLog> searchLogTop10 = searchService.findTop10();

        SearchApiResponse searchApiResponse = new SearchApiResponse();
        searchApiResponse.setValues(searchLogTop10);

        return new ResponseEntity<>(searchApiResponse, HttpStatus.OK);
    }
}
