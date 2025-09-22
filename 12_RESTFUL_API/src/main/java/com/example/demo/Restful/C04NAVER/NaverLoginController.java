package com.example.demo.Restful.C04NAVER;

import com.example.demo.Restful.C03KAKAO.KakaoLoginController;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequestMapping("/naver")
public class NaverLoginController {

    private String CLIENT_ID="";
    private String CLIENT_SECRET="";
    private String REDIRECT_URL="http://localhost:8099/naver/getCode";

    private  String code;
    private  String state;
    private NaverTokenResponse naverTokenResponse;

    @GetMapping("/login")
    public String login(){
        log.info("GET /naver/login");
        return "redirect:https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+CLIENT_ID+"&state=STATE_STRING&redirect_uri="+REDIRECT_URL;
    }

    @GetMapping("/getCode")
    public String getCode(String code,String state){
        log.info("GET /naver/getCode....code : " + code + " state : " + state);
        this.code = code;
        this.state = state;
        return "forward:/naver/getAccessToken";
    }

    @GetMapping("/getAccessToken")
    public String getAccessToken(){
        log.info("GET /naver/getAccessToken....");

        String url = "https://nid.naver.com/oauth2.0/token";

        RestTemplate restTemplate = new RestTemplate();

        // 요청 헤더 설정
        HttpHeaders header = new HttpHeaders();
        // 요청 바디  파라미터 설정
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id",CLIENT_ID);
        params.add("client_secret",CLIENT_SECRET);
        params.add("code",code);
        params.add("state",state);

        HttpEntity< MultiValueMap<String,String>  > entity = new HttpEntity<>(params,header);

        // 요청 후 응답 확인
        ResponseEntity<NaverTokenResponse> response =
                restTemplate.exchange(url, HttpMethod.POST,entity, NaverTokenResponse.class);
        System.out.println(response.getBody());
        this.naverTokenResponse = response.getBody();

        // Main으로 리다이렉트
        return "redirect:/naver";
    }

    @GetMapping
    public String main(Model model){
        log.info("GET /naver/index...");

        String url = "https://openapi.naver.com/v1/nid/me";

        RestTemplate restTemplate = new RestTemplate();

        // 요청 헤더 설정
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization","Bearer "+ naverTokenResponse.getAccess_token());
        // 요청 바디  파라미터 설정(x) 생략

        HttpEntity entity = new HttpEntity(header);

        // 요청 후 응답 확인
        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.POST,entity, String.class);
        System.out.println(response.getBody());

        // 뷰로 전달
//        model.addAttribute("profile",response.getBody());
//        String nickname = response.getBody().getProperties().getNickname();
//        String image_url = response.getBody().getProperties().getThumbnail_image();
//        String email = response.getBody().getKakao_account().getEmail();
        // model 은 스프링단독으로 쓸 때만
//        model.addAttribute("nickname",nickname);
//        model.addAttribute("image_url",image_url);
//        model.addAttribute("email",email);
//
        return "naver/index";
    }

    // NAVER TOKEN RESPONSE
    @Data
    private static class NaverTokenResponse{
        public String access_token;
        public String refresh_token;
        public String token_type;
        public String expires_in;
    }
}
