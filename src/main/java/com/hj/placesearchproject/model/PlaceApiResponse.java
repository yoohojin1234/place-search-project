package com.hj.placesearchproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class PlaceApiResponse {
    private String releaseVersion;
    private List<Map<String, Object>> places;

    public PlaceApiResponse() { this.releaseVersion  = "v1.0"; }

    public void setPlacesAsList(List<String> places) {
        List<Map<String, Object>> titleList = new ArrayList<>();
        for(String place : places) {
            Map<String, Object> m = new HashMap<>();
            m.put("title", place);
            titleList.add(m);
        }
        this.places = titleList;
    }
}
