package com.example.demo.config.auth.logoutHandler;

import com.example.demo.config.auth.PrincipalDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String KAKAO_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.logout.redirect.uri}")
    private String KAKAO_REDIRECT_URI;


    // 로컬서버 로그아웃 이후 추가 처리(ex. 카카오인증서버 연결해제..)
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("CustomLogoutSuccessHandler's onLogoutSuccess invoke..! " + authentication);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            String provider = principalDetails.getDto().getProvider();
            System.out.println("provider : " + provider);

            if (provider!=null && provider.startsWith("Kakao")) {
                System.out.println("!!!" + KAKAO_CLIENT_ID + " " + KAKAO_REDIRECT_URI);
                response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id=" + KAKAO_CLIENT_ID + "&logout_redirect_uri=" + KAKAO_REDIRECT_URI);
                return;
            } else if (provider!=null && provider.startsWith("Naver")) {
                response.sendRedirect("https://nid.naver.com/nidlogin.logout?returl=https://www.naver.com/");
                return ;
            } else if (provider!=null && provider.startsWith("Google")) {
                response.sendRedirect("https://accounts.google.com/Logout");
                return ;
            }
        response.sendRedirect("/");

    }
}
