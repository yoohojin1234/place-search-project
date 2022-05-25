package com.hj.placesearchproject.model.kakao;

import lombok.Data;

@Data
public class KakaoMeta {
    private boolean isEnd;
    private int pageableCount;
    private KakaoSameName sameName;
    private int totalCount;
}
