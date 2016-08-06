/*
 * Creation : 2016年8月1日
 */
/**
 * 
 */
package com.boz.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PageController.
 * 
 * @author E470756
 */
@Controller
public class PageController {

    @RequestMapping("/page/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/page/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/page/regSuccess")
    public String registerSuccess() {
        return "regSuccess";
    }
}
