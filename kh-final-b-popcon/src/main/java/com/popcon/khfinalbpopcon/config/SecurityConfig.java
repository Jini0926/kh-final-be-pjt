package com.popcon.khfinalbpopcon.config;

import com.popcon.khfinalbpopcon.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


// Oauth 로그인 진행 순서
// 1. 인가 코드 발급(회원 인증)
// 2. 엑세스 토큰 발급(접근 권한 부여)
// 3. 액세스 토큰을 이용해 사용자 정보 불러오기
// 4. 불러온 사용자 정보를 토대로 자동 회원가입/로그인 진행
// ※ 소셜 플랫폼의 로그인과 프로젝트 앱의 로그인은 별개임을 명시!!

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    // @Bean -> 해당 메소드의 리턴되는 오브젝트를 IoC로 등록해줌
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/main/**").authenticated()
                .anyRequest().permitAll()

                .and()
                .logout()
                .logoutSuccessUrl("/login")

                .and()
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/main")
                // 소셜 로그인이 완료되면 oauth2-client 라이브러리가 자동으로 <엑세스토큰 + 사용자 정보>를 전달해준다
                // 라이브러리를 사용하지 않는 경우, 엑세스토큰으로 사용자 정보를 직접 요청해야한다!!
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
    }

}
