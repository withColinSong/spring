package cos.jwt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import cos.jwt.config.auth.PrincipalDetails;
import cos.jwt.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;


// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter가 있음.
// /login 요청해서 username, password 전송하면(post)
// UsernamePasswordAuthenticationFilter 동작을 함
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // /login을 요청하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter: 로그인 시도중");

        // 1. username, password 받아서
        try {

            ObjectMapper om = new ObjectMapper(); //json 데이터 parse
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println("attemptAuthentication" + user);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            // PrincipalDetailsService loadUserByUsername() 함수가 실행됨
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println(principalDetails.getUser().getUsername()); // => 로그인이 됐다는 뜻

            // authentication 객체가 session 영역에 저장됨.
            // 리턴의 이유는 권한 관리를 security가 대신 해주기 대문에 편하려고 한다.
            // 굳이 jwt 토근을 사용하면서 세션을 만들 이유가 없는데, 단지 권한 처리 때문에 session 넣어준다.
            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // attemptAuthentication 실행 후 인증이 정상적으로 완료가 되었다면 아래 메서드를 실행
    // jwt토근을 만들어서 request요청한 데이터에게 jwt 토큰을 response 해주면 됨
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request
            , HttpServletResponse response
            , FilterChain chain
            , Authentication authResult) throws IOException, ServletException {
        System.out.println("정상적인 인증");

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        System.out.println("principalDetails" + principalDetails.getUser().getUsername());
        // 1000 => 1초, 1000 => 1분
        String jwtToken = JWT.create()
                .withSubject("cos토큰")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000*30))) // 만료시간
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512("cos"));

        System.out.println("jwtToken" + jwtToken);
        response.addHeader("Authorization", "Bearer " + jwtToken);

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
