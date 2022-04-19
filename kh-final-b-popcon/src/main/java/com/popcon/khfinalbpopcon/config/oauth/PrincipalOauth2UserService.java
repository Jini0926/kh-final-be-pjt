package com.popcon.khfinalbpopcon.config.oauth;

import com.popcon.khfinalbpopcon.model.User;
import com.popcon.khfinalbpopcon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    // 소셜 로그인 완료 -> (oauth2-client 라이브러리) code, AccessToken 자동 발급 -> userRequest 에 담음
    // userRequest 데이터로 회원 프로필 정보를 받아주는 것이 super.loadUser 함수이다
    // userRequest 데이터에는 소셜 서버로부터 받은 소셜 플랫폼 정보, 인가 코드, 액세스토큰이 들어있다

    @Autowired
    UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 로그 출력
        System.out.println("getAttributes : " + super.loadUser(userRequest).getAttributes());
        System.out.println("getClientRegistration : " + userRequest.getClientRegistration());
        System.out.println("getAccessToken : " + userRequest.getAccessToken().toString());

        // 강제로 회원 가입 진행
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

        Long kakaoId = oAuth2User.getAttribute("id");
        String profileImg = (String)kakaoProfile.get("profile_image_url");
        String nickname = (String)kakaoProfile.get("nickname");
        String email = (String)kakaoAccount.get("email");
        String role = "ROLE_USER";


        User userEntity = userRepository.findByKakaoId(kakaoId);
        if(userEntity == null) {
            userEntity = User.builder()
                    .kakaoId(kakaoId)
                    .kakaoProfileImg(profileImg)
                    .kakaoNickname(nickname)
                    .kakaoEmail(email)
                    .userRole(role)
                    .build();
            userRepository.save(userEntity);
        }

        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }

}
