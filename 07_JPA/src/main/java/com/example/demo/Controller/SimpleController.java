package com.example.demo.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // 사용자의 요청을 받는 Bean이 만들어짐
@RequestMapping("/simple")
@Slf4j
public class SimpleController {

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public void test1()
    {
        log.info("GET /simple/test1...");
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
         public String test2()
    {
        log.info("GET /simple/test2...");
        return "simple/abcd";
    }
                                                // 기본방식 : GET
    @RequestMapping(value = "/test3", method = {RequestMethod.GET,RequestMethod.POST})
    public void test3()
    {
        log.info("GET/POST /simple/test3...");
    }
}
