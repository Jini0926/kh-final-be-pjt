package com.popcon.khfinalbpopcon.controller;

import com.popcon.khfinalbpopcon.config.oauth.PrincipalDetails;
import com.popcon.khfinalbpopcon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SocialLoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String getLogin() {
        return "/html/login";
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String loginTest(
            Authentication authentication,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        System.out.println("/test/oauth/login ===============================");

        System.out.println("authentication : " + authentication.getAuthorities());
        System.out.println("principalDetails = " + principalDetails.getUser());
        System.out.println("=======================================");
        System.out.println("getAuthorities = " + authentication.getAuthorities());
        System.out.println("getDetails = " + authentication.getDetails());
        System.out.println("getName = " + authentication.getName());
        System.out.println("getPrincipal = " + authentication.getPrincipal());


        return "OAuth 세션 정보 확인하기";
    }

//    @GetMapping
//    public String loginPage() {
//        return "/html/login";
//    }
//
//    // 프론트에서 인가코드 돌려 받는 주소 // 카카오 redirect uri
//    @GetMapping("/oauth2/code/kakao")
//    public @ResponseBody String getLogin(String code) {
//
//        // 넘어온 인가 코드를 통해 access_token 발급
//        OauthToken oauthToken = getAccessToken(code);
//
//        // 발급 받은 accessToken 으로 카카오 회원 정보 요청
//        KakaoProfile kakaoProfile = findProfile(oauthToken.getAccess_token());
//
//        System.out.println("액세스토큰 : " + oauthToken.getAccess_token());
//        System.out.println("카카오 아이디 : " + kakaoProfile.getId());
//        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
//
//        return "회원정보 가져오기 성공!";
//    }
//
//    public KakaoProfile findProfile(String token) {
//        // POST 방식으로 key=value 데이터 요청
//        RestTemplate rt = new RestTemplate();
//
//        // HttpHeader 오브젝트 생성
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + token);
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // HttpHeader 와 HttpBody 정보를 하나의 오브젝트에 담음
//        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
//                new HttpEntity<>(headers);
//
//        // Http 요청 (POST 방식) 후, response 변수의 응답을 받음
//        ResponseEntity<String> kakaoProfileResponse = rt.exchange(
//                "https://kapi.kakao.com/v2/user/me",
//                HttpMethod.POST,
//                kakaoProfileRequest,
//                String.class
//        );
//
//        // JSON 응답을 객체로 변환
//        ObjectMapper objectMapper = new ObjectMapper();
//        KakaoProfile kakaoProfile = null;
//        try {
//            kakaoProfile = objectMapper.readValue(kakaoProfileResponse.getBody(), KakaoProfile.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        return kakaoProfile;
//    }
//
//    public OauthToken getAccessToken(String code) {
//
//        // POST 방식으로 key=value 데이터 요청
//        RestTemplate rt = new RestTemplate();
//
//        // HttpHeader 오브젝트 생성
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // HttpBody 오브젝트 생성
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", "00ce48db774c8e8effcc16b9758ad126");
//        params.add("redirect_uri", "http://localhost:8080/login/oauth2/code/kakao");
//        params.add("code", code);
//        params.add("client_secret", "lovqZJ6yfDcsnjwBPObWgvwg2WkXiKaX");
//
//        // HttpHeader 와 HttpBody 정보를 하나의 오브젝트에 담음
//        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
//                new HttpEntity<>(params, headers);
//
//        // Http 요청 (POST 방식) 후, response 변수의 응답을 받음
//        ResponseEntity<String> accessTokenResponse = rt.exchange(
//                "https://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                kakaoTokenRequest,
//                String.class
//        );
//
//        // JSON 응답을 객체로 변환
//        ObjectMapper objectMapper = new ObjectMapper();
//        OauthToken oauthToken = null;
//        try {
//            oauthToken = objectMapper.readValue(accessTokenResponse.getBody(), OauthToken.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        return oauthToken;
//    }

}
