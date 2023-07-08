package yoon.test.oAuthTest2.enums;

import lombok.Getter;

@Getter
public enum Role {

    GUEST("ROLE_GUEST"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String key;

    Role(String key){
        this.key = key;
    }

}
