package cn.linj2n.verification.web.rest;

import cn.linj2n.verification.domain.User;
import cn.linj2n.verification.service.EmailService;
import cn.linj2n.verification.service.UserService;
import cn.linj2n.verification.web.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger logger = LoggerFactory.getLogger(AccountResource.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmailService emailService;


    @RequestMapping(value = "account/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerAccount(@Valid @RequestBody UserDTO userDTO, HttpServletRequest request) {
        logger.info("Register a new Account: -----> UserDto {}", userDTO.toString());

        // 1. check that the account doesn’t already Exist
        if (userService.checkIfExitUserByLoginOrEmail(userDTO.getLogin(), userDTO.getEmail())) {
            Locale locale = new Locale("zh", "CN");
            return new ResponseEntity<>(messageSource.getMessage("message.emailOrLogin.alreadyExist", null, locale), HttpStatus.BAD_REQUEST);
        }
        // 2. create new user with userDto information
        User user = userService.createUserInformation(userDTO.getLogin(), userDTO.getPassword(), userDTO.getLogin(), userDTO.getEmail());

        // 3. send activation email
        emailService.sendActivationEmail(user, "http://localhost:8080");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "account/activate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> activateAccount(@RequestParam(value = "key") String key) {
        // TODO: activateAccount
        return null;
    }

    @RequestMapping(value = "/account/change_password",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@RequestBody String password) {

        // TODO: change Password
        return null;
    }

    @RequestMapping(value = "/account/existence",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkIfExistUser(@RequestParam(value = "login", required = false, defaultValue = "") final String login,
                                              @RequestParam(value = "email", required = false, defaultValue = "") final String email) {

        // TODO:
        Boolean exited = userService.checkIfExitUserByLoginOrEmail(login, email);
        logger.info("Get url(/account/existence) result : --------> " + exited);
        return exited ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
