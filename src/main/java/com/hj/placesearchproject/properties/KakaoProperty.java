package com.hj.placesearchproject.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.kakao")
public class KakaoProperty {
    private String apiKey;
    private String url;
}
