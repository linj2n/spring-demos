package cn.linj2n.verification.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @RequestMapping(value = {"/admin/index"},method = RequestMethod.GET)
    public String getIndex(){
        return "admin/index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String getRegister () {
        return "register";
    }
    @RequestMapping(value = "/blank",method = RequestMethod.GET)
    public String getBlank () {
        return "blank";
    }
}
