package com.hj.placesearchproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class SearchApiResponse {
    private String releaseVersion;
    private List<SearchLog> values;

    public SearchApiResponse() { this.releaseVersion  = "v1.0"; }
}
