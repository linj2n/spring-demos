package cn.linj2n.hellospringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HellowolrdController {
    @RequestMapping(value = "/hello")
    public ModelAndView sayHello() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message","Hello world!");
        mv.setViewName("helloworld");
        return mv;
    }
}
