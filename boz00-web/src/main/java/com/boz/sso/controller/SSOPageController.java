/*
 * Creation : 2016年8月1日
 */
/**
 * 
 */
package com.boz.sso.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PageController.
 * 
 * @author E470756
 */
@Controller
@Scope("prototype")
public class SSOPageController {

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
