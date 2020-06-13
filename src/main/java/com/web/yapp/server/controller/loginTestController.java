package com.web.yapp.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginTestController {
    @GetMapping("/testlogin")
    public String testlogin(){
        return "login";
    }
}
