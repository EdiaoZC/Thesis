package com.thesis.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/25 20:09
 * @Description:
 */
@Controller
public class IndexController {

    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("username", "110695");
        model.addAttribute("age", 10);
        return "index";
    }

    @RequestMapping({"/main"})
    public String main() {
        return "/WEB-INF/views/page/main.html";
    }


}
