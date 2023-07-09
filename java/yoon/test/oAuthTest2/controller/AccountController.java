package yoon.test.oAuthTest2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yoon.test.oAuthTest2.service.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login/oauth2/code")
public class AccountController {


    @GetMapping("/naver")
    public ResponseEntity<?> loginNaver(){
        System.out.println("Naver");
        return ResponseEntity.ok("Naver Login Seccess");
    }

    @GetMapping("/google")
    public ResponseEntity<?> loginGoogle(){
        System.out.println("Google");
        return ResponseEntity.ok("Google Login Seccess");
    }

    @GetMapping("/kakao")
    public ResponseEntity<?> loginKakao(){
        System.out.println("Kakao");
        return ResponseEntity.ok("Kakao Login Seccess");
    }
}
