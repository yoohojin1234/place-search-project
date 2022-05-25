package com.hj.placesearchproject;

import com.hj.placesearchproject.properties.KakaoProperty;
import com.hj.placesearchproject.properties.NaverProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableConfigurationProperties({
    KakaoProperty.class,
    NaverProperty.class
})
@EnableAspectJAutoProxy
public class PlaceSearchProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaceSearchProjectApplication.class, args);
    }

}
