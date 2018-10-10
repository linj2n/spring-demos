package cn.linj2n.verification.web;

import cn.linj2n.verification.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

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
    public String activateAccount(Locale local,@RequestParam(value = "activationKey",required=true) final String activationKey, RedirectAttributes redirectAttrs) {
        logger.info("Get URL /account/activate with key : {}" + activationKey);
        if (userService.activateRegistration(activationKey).isPresent()) {
            redirectAttrs.addAttribute("message",messageSource.getMessage("account.activatedSuccessfully",null,local));
        } else {
            redirectAttrs.addAttribute("message",messageSource.getMessage("account.failedToActivate",null,local));
        }
        return "redirect:/login";
    }
}
