package com.example.demo.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {

    @GetMapping("/add")
    public void add_memo_get(){
        log.info("GET /memo/add...");
    }

    @PostMapping("/add")
    public void add_memo_post(){
        log.info("POST /memo/add...");
    }





}
