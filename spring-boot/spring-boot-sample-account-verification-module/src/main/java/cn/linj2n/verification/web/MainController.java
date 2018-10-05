package cn.linj2n.verification.web;

import cn.linj2n.verification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    UserService userService ;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/admin/index"}, method = RequestMethod.GET)
    public String getIndex() {
        return "admin/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister() {
        return "register";
    }

    @RequestMapping(value = "/blank", method = RequestMethod.GET)
    public String getBlank() {
        return "blank";
    }

    @RequestMapping(value = "/account/activate", method = RequestMethod.GET)
    public String activateAccount(final WebRequest request, @RequestParam(value = "key") final String key, RedirectAttributes redirectAttrs) {
        if (userService.activateRegistration(key).isPresent()) {
            redirectAttrs.addAttribute("message",messageSource.getMessage("account.activatedSuccessfully",null,request.getLocale()));
            return "redirect:/login";
        } else {
            redirectAttrs.addAttribute("errorInfo",messageSource.getMessage("account.failedToActivate",null,request.getLocale()));
            // TODO: Add error page.
            return "redirect:/error";
        }
    }
}
