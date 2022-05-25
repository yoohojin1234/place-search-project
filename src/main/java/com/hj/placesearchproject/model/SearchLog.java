package com.hj.placesearchproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class SearchLog {
    @JsonIgnore
    private int id;

    private String searchPlace;
    private int count;
}
