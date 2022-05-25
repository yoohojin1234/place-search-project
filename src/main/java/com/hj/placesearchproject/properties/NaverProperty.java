package com.hj.placesearchproject.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(value = "app.naver")
public class NaverProperty {
    private String clientId;
    private String clientSecret;
    private String url;
}
