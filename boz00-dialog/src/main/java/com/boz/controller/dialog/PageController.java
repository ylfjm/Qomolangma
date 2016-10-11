package com.boz.controller.dialog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PageController.
 * 
 * @author E470756
 */
@Controller
public class PageController {

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
