package com.example.security01.config;

import com.example.security01.config.oauth.PrincipalOauth2UserService;
import com.example.security01.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// 구글 로그인
// 1. 코드 받기(인증)
// 2. 액세스토크(권한)
// 3. 사용자 프로필 정보를 가져오고, 그 정보를 토대로 회원가입을 자동으로 진행시킴
// 4-2 (이메일, 전화번호, 이름, 아이디) 쇼핑몰 -> 집주소 등등

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨.
public class SecurityConfig extends WebSecurityConfigurerAdapter { // => 스프링 시큐리티 필터

    @Autowired
    PrincipalOauth2UserService principalOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() // 인증만 되면 들어갈 수 있는 주소
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")      // 로그인 page url
                .loginProcessingUrl("/login") // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인 진행
                .defaultSuccessUrl("/")      // 만약 다른 url를 통해 로그인을 했다면 해당 url로 자동으로 이동됨
                .and()
                .oauth2Login()
                .loginPage("/loginForm")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
                // 구글 로그인이 완료된 뒤의 후처리가 필요한대, 이때 코드가 리턴되는 것이 아니라 엑세스토근, 사용자 프로필정보를 가져옴)

    }

    @Bean // 해당 메서드의 리턴되는 오브젝트를 IoC를 등록해준다.
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

}
