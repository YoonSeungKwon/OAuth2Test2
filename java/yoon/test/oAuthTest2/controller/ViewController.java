package yoon.test.oAuthTest2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import yoon.test.oAuthTest2.service.AccountService;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final AccountService accountService;

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

}
