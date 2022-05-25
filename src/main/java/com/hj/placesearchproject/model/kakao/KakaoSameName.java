package com.hj.placesearchproject.model.kakao;

import lombok.Data;
import java.util.List;

@Data
public class KakaoSameName {
    private String keyword;
    private List<String> region;
    private String selectedRegion;
}
