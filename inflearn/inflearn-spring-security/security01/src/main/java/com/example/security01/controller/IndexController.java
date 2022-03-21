package com.example.security01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // view를 리턴함
public class IndexController {

    @GetMapping({"", "/"})
    public String index() {
        // 머스테치 기본폴더 src/main/resources
        // 뷰리졸버 설정 : templates(prefix), mustache(suffix)생략 가능
        return "index";
    }

    @GetMapping("user")
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping("admin")
    public @ResponseBody String admin() {
        return "admin";
    }

    @GetMapping("manager")
    public @ResponseBody String manager() {
        return "manager";
    }

    // 스프링 시큐리티가 낚아챔 => securityConfig을 생성하고나서는 낚아채지 않음
    @GetMapping("login")
    public @ResponseBody String login() {
        return "login";
    }

    @GetMapping("join")
    public @ResponseBody String join() {
        return "join";
    }

    @GetMapping("joinProc")
    public @ResponseBody String joinProc() {
        return "회원가입 완료됨";
    }


}
