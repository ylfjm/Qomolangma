/*
 * Creation : 2016年7月22日
 */
/**
 * 
 */
package com.boz.web.controller;

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
public class PageController {

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/main")
    public String showMain() {
        return "main";
    }

    @RequestMapping("/aboutme")
    public String showAboutMe() {
        return "aboutme";
    }

    @RequestMapping("/study")
    public String showStudy() {
        return "study";
    }

    @RequestMapping("/msgboard")
    public String showMsgBoard() {
        return "msgboard";
    }

    @RequestMapping("/urmlv")
    public String urmlv() {
        return "urmlv";
    }
}
