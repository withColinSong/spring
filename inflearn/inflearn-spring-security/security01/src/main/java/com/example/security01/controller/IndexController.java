package com.example.security01.controller;

import com.example.security01.config.auth.PrincipalDetails;
import com.example.security01.model.User;
import com.example.security01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // view를 리턴함
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"", "/"})
    public String index() {
        // 머스테치 기본폴더 src/main/resources
        // 뷰리졸버 설정 : templates(prefix), mustache(suffix)생략 가능
        return "index";
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String loginTest(Authentication authentication) {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println(oAuth2User.getAttributes());
        return "/test/oauth/login";
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
    @GetMapping("loginForm")
    public String login() {
        return "loginForm";
    }

    @GetMapping("joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("join")
    public String join(User user) {
        user.setRole("ROLE_USER");

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        userRepository.save(user);
        return "redirect:/loginForm";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "개인정보";
    }

//    @PreAuthorize 메서드 실행 전 여러개 권한을 걸고 싶을 때
//    @PostAuthorize 메서드 실행 후 여러개 권한을 걸고 싶을 때
    @PreAuthorize("HasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/data")
    public @ResponseBody String data() {
        return "데이터정보";
    }

}
