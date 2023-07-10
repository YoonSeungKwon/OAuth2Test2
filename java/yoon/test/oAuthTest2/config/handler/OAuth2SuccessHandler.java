package yoon.test.oAuthTest2.config.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import yoon.test.oAuthTest2.domain.Accounts;
import yoon.test.oAuthTest2.jwt.JwtProvider;
import yoon.test.oAuthTest2.repository.AccountRepository;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final HttpSession httpSession;
    private final AccountRepository accountRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println(httpSession.getAttribute("email"));
        Accounts accounts = accountRepository.findAccountsByEmail((String)httpSession.getAttribute("email"));
        String acc = jwtProvider.createAccessToken(accounts);
        String ref = jwtProvider.createRefreshToken();

        response.setHeader("Authorization", acc);
        response.setHeader("X-Refresh-Token", ref);

        String provider = accounts.getProvider().getKey();

        if("Naver".equals(provider))
            response.sendRedirect("/login/oauth2/naver");
        else if ("Kakao".equals(provider))
            response.sendRedirect("/login/oauth2/kakao");
        else
            response.sendRedirect("/login/oauth2/google");


    }
}
