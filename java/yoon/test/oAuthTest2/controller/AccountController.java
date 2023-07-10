package yoon.test.oAuthTest2.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yoon.test.oAuthTest2.service.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login/oauth2")
public class AccountController {


    @PostMapping("/naver")
    public String loginNaver(HttpServletRequest req){
        System.out.println("Naver");
        return "AccessToken :" + req.getHeader("Authorization") +
                "RefreshToken :" + req.getHeader("X-RefreshToken");
    }

    @GetMapping("/google")
    public ResponseEntity<?> loginGoogle(HttpServletRequest req){
        System.out.println("Google");
        return ResponseEntity.ok(req.getHeader("Authorization"));
    }

    @GetMapping("/kakao")
    public ResponseEntity<?> loginKakao(HttpServletRequest req){
        System.out.println("Kakao");
        return ResponseEntity.ok(req.getHeader("Authorization"));
    }
}
