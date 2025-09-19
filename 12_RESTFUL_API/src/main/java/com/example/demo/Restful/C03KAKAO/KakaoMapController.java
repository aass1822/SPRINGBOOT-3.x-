package com.example.demo.Restful.C03KAKAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kakao")
@Slf4j
public class KakaoMapController {

    @GetMapping("/map")
    public void map(){
        log.info("GET /kakap/map...");


    }

}
