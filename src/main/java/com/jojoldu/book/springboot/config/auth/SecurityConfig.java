package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.CustomUserTypesOAuth2UserService;

@RequiredArgsConstructor
@EnableWebSecurity   // << spring security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console.화면 사용하기위해 disable
                .and()
                    .authorizeRequests() // url별 권한관리 설정옵션의 시작점
                    .antMatchers(".", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated() // 설정이외의 나머지 url
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login() // 로그인기능 설정진입점
                        .userInfoEndpoint() // 로그인성공이후 사용자정보 가져올때의 설정들 담당
                            .userService(customOAuth2UserService); // 로그인성공후 후속조치 진행할 인터페이스 구현체 등록
    }
}
