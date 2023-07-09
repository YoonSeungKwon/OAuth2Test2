package yoon.test.oAuthTest2.vo.response;

import lombok.Builder;
import lombok.Getter;
import yoon.test.oAuthTest2.enums.Providers;

import java.util.Map;

@Getter
public class OAuthAttribute {

    private String email;

    private String name;

    private Providers provider;

    private String attributeKey;

    private Map<String, Object> attributes;

    @Builder
    OAuthAttribute(String email, String name, Providers provider, String attributeKey, Map<String, Object> attributes){
        this.email = email;
        this.name = name;
        this.provider = provider;
        this.attributeKey = attributeKey;
        this.attributes = attributes;
    }

}
