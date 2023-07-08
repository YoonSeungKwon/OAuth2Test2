package yoon.test.oAuthTest2.vo.response;

import lombok.Builder;
import lombok.Getter;
import yoon.test.oAuthTest2.enums.Role;

import java.util.Map;

@Getter
public class OAuthAttribute {

    private String email;

    private String name;

    private String picture;

    private String attributeKey;

    private Map<String, Object> attributes;

    @Builder
    OAuthAttribute(String email, String name, String picture, String attributeKey, Map<String, Object> attributes){
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.attributeKey = attributeKey;
        this.attributes = attributes;
    }

}
