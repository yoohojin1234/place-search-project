package com.hj.placesearchproject.model.naver;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class NaverSearchPlace {
    private Date lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<NaverItem> items;
}
