package com.hj.placesearchproject.model.kakao;

import lombok.Data;
import java.util.List;

@Data
public class KakaoSearchPlace {
    private List<KakaoDocument> documents;
    private KakaoMeta meta;
}
