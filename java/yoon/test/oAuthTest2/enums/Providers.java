package yoon.test.oAuthTest2.enums;

import lombok.Getter;

@Getter
public enum Providers {

    GOOGLE(1, "Google"),
    NAVER(2, "Naver"),
    KAKAO(3, "Kakao");

    private int value;
    private String key;

    Providers(int value, String key){
        this.value = value;
        this.key = key;
    }
}
