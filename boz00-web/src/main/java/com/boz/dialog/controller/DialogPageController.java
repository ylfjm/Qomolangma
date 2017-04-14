package com.boz.dialog.controller;

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
@RequestMapping("/dialog")
public class DialogPageController {

    @RequestMapping("/")
    public String index() {
        return "pageOne";
    }

    @RequestMapping("/pageOne")
    public String pageOne() {
        return "pageOne";
    }

    @RequestMapping("/pageTwo")
    public String pageTwo() {
        return "pageTwo";
    }

    @RequestMapping("/showDialog")
    public String showDialog() {
        return "showDialog";
    }

}
