package com.example.demo.Handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class CustomHandler implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("CUSTOMHANDELR's handleRequest invoke!!");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("memo/add");
        return modelAndView;
    }

    // REST요청 처리 HANDLER
    @ResponseBody
    public String HelloWorld(){
        System.out.println("CUSTOMHANDELR's HelloWorld Method Invoke..!!");
        return "HELLO_WORLD TEST!";
    }
}
