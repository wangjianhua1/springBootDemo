package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by wangjianhua3 on 2018/2/26.
 */
@Controller
public class DemoController extends BaseController {

    @Value("${myname}")
    private String myname;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        log.info("=springboot=" + myname);
        request.setAttribute("userName", myname);
        HttpSession session = request.getSession();
        session.setAttribute("test","test");
        return "index";
    }

    public static void main(String[] args) {

        System.out.println("==" + 0x1.fffffep-2f);
        System.out.println("==" + 0x3.fffffep-2f);
        System.out.println("==" + Math.ceil(1.11111));
    }


}
