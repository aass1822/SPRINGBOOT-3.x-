package com.example.demo.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// ApplicationContext(특정자원영역) 안에 (사용자의 요청을 받는) Bean(내장객체)을 만드는 작업
@Controller
@Slf4j
public class HomeController {

    @GetMapping("/") // 최상위 경로
    public String home(){
//        System.out.println("GET /");
        log.info("GET /....");
        return "index";
    }

}
