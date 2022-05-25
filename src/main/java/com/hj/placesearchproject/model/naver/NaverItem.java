package com.hj.placesearchproject.model.naver;

import lombok.Data;

@Data
public class NaverItem {
    private String title;
    private String link;
    private String category;
    private String description;
    private String telephone;
    private String address;
    private String roadAddress;
    private String mapx;
    private String mapy;
}
