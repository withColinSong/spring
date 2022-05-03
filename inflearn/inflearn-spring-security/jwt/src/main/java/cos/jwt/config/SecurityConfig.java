package cos.jwt.config;

import cos.jwt.config.jwt.JwtAuthenticationFilter;
import cos.jwt.filter.MyFilter3;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // spring security filter 실행전에 아래와 같이 설정해야 함.
        // 우리가 설정한 필터보다 스프링 시큐리티 필터가 먼저 동작한다.
        http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 세션 사용하지 않음
        .and()
        .addFilter(corsFilter) // @CrossOrigin(인증x), 시큐리티 필터에 등록 인증
        .addFilter(new JwtAuthenticationFilter(authenticationManager())) // AuthenticationManager를 던져줘야 함
        .formLogin().disable() // disable를 하면 formLogin(), loginProcessingUrl()가 실행하지 않는다.
        .httpBasic().disable() // bearer 방식을 사용할 때
        .authorizeRequests()
        .antMatchers("/api/v1/user/**")
        .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
        .antMatchers("/api/v1/manager/**")
        .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
        .antMatchers("/api/v1/admin/**")
        .access("hasRole('ROLE_ADMIN')")
        .anyRequest().permitAll();
//        .and()
//        .formLogin()
//        .loginProcessingUrl("/login");

    }
}
