package cn.linj2n.hello.web.controller;

import cn.linj2n.hello.domain.User;
import cn.linj2n.hello.web.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/users/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable String username) {
        logger.info("Username ------> {}",username);
        if (!username.equals("zhangsan")) {
            throw new UserNotFoundException();
        }
        return new ResponseEntity<>(new User("张三", "zhangsan@host.com"), HttpStatus.OK);
    }
}
