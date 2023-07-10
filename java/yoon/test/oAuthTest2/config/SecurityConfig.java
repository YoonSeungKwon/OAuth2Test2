package yoon.test.oAuthTest2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import yoon.test.oAuthTest2.config.handler.OAuth2SuccessHandler;
import yoon.test.oAuthTest2.service.OAuth2CustomService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2CustomService oAuth2CustomService;
    private final OAuth2SuccessHandler successHandler;
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth.anyRequest().permitAll())

                //Session
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //OAuth
                .oauth2Login(login ->{
                    login.userInfoEndpoint(endPoint-> endPoint.userService(oAuth2CustomService));
                    login.successHandler(successHandler);
                    })

                .build();
    }
}
