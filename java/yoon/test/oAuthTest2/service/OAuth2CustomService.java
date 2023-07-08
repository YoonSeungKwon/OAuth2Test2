package yoon.test.oAuthTest2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import yoon.test.oAuthTest2.domain.Accounts;
import yoon.test.oAuthTest2.repository.AccountRepository;
import yoon.test.oAuthTest2.vo.response.OAuthAttribute;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2CustomService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final AccountService accountService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

        OAuth2User user = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String attributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttribute attribute = of(registrationId, attributeName,user.getAttributes());

        Accounts accounts = accountService.saveOAuth(attribute);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(accounts.getRoleKey())), attribute.getAttributes(),
                attribute.getAttributeKey());
    }

    public static OAuthAttribute of(String registrationId, String attributeName, Map<String, Object> attributes){
        if(registrationId.equals("naver"))
            return ofNaver(attributeName, attributes);
        return ofGoogle(attributeName, attributes);
    }

    public static OAuthAttribute ofNaver(String attributeName, Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthAttribute.builder()
                .email(String.valueOf(response.get("email")))
                .name(String.valueOf(response.get("name")))
                .picture(String.valueOf(response.get("profile_image")))
                .attributeKey(attributeName)
                .attributes(response)
                .build();
    }

    public static OAuthAttribute ofGoogle(String attributeName, Map<String, Object> attributes){
        return OAuthAttribute.builder()
                .email(String.valueOf(attributes.get("email")))
                .name(String.valueOf(attributes.get("name")))
                .picture(String.valueOf(attributes.get("picture")))
                .attributeKey(attributeName)
                .attributes(attributes)
                .build();
    }
}
