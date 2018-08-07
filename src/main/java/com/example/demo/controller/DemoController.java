package com.example.demo.controller;

import com.example.demo.dao.OrderDao;
import com.example.demo.utils.MyBeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by wangjianhua3 on 2018/2/26.
 */
@Controller
@RequestMapping("/")
public class DemoController extends BaseController {

    @Value("${myname}")
    private String myname;
    @Resource
    private OrderDao orderDao;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        log.info("=springboot=" + myname);
        int count = this.orderDao.getCount();
        log.info("=orderDao=" + count);
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
