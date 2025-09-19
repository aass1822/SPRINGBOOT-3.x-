package com.example.demo.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/rest2")
public class RestTest2Controller {

    @GetMapping("/test1")
    @ResponseBody // 해당 함수요청에 한해서 비동기처리 / 데이터처리
    public String t1(){
        log.info("GET /rest2/test1....");
        return "HELLO WORLD";
    }
















}
