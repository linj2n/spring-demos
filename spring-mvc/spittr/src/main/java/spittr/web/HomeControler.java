package spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // 声明为一个控制器
@RequestMapping({"/","/home"})
public class HomeControler {
    @RequestMapping(method = RequestMethod.GET)     // 处理对"/"的 GET 请求
    public String home() {
        return "home";  // 返回 "home" 视图名
    }
}
