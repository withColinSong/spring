package com.example.security01.config.oauth;

import com.example.security01.config.auth.PrincipalDetails;
import com.example.security01.model.User;
import com.example.security01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
     * 구글로그인 버튼 클릭 -> 구글로그인창 -> 로그인을 완료 -> code를 리턴(Oauth-client 라이브러리) -> 엑세스토큰 요청
     * userRequest 정보 -> loadUser함수호출 -> 구글로부터 회원프로필을 받아준다.
     */

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getClientId(); // google
        String provderId = oAuth2User.getAttribute("sub");
        String username = provider + "_" + provderId;
        String role = "ROLE_USER";
        String email = oAuth2User.getAttribute("email");

        User userEntity = userRepository.findByUsername(username);
        if(userEntity == null) {
            // 회원가입
            userEntity = User.builder()
                    .username(username)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(provderId)
                    .build();
            userRepository.save(userEntity);
        }

        // 회원가입을 강제로 진행 예정
        // 세션 정보로 들어가게됨
        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
