package com.hj.placesearchproject.model.kakao;

import lombok.Data;

@Data
public class KakaoDocument {
    public String addressName;
    public String categoryGroupCode;
    public String categoryGroupName;
    public String categoryName;
    public String distance;
    public String id;
    public String phone;
    public String placeName;
    public String placeUrl;
    public String roadAddressName;
    public float x;
    public float y;
}
