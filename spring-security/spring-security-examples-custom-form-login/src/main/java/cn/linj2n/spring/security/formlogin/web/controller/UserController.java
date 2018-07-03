package cn.linj2n.spring.security.formlogin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class UserController {

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/blank")
    public String blank() {
        return "blank";
    }
}
