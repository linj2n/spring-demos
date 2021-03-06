package cn.linj2n.verification.web.rest;

import cn.linj2n.verification.domain.Authority;
import cn.linj2n.verification.domain.User;
import cn.linj2n.verification.security.SecurityUtil;
import cn.linj2n.verification.service.EmailService;
import cn.linj2n.verification.service.UserService;
import cn.linj2n.verification.web.dto.ResponseDTO;
import cn.linj2n.verification.web.dto.UserDTO;
import cn.linj2n.verification.web.dto.support.ResponseCode;
import cn.linj2n.verification.web.errors.ErrorResponse;
import cn.linj2n.verification.web.utils.ResponseGenerator;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger logger = LoggerFactory.getLogger(AccountResource.class);

    private static final String BASE_URL = "http://localhost:8080";

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmailService emailService;


    @RequestMapping(value = "/v1/account",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAccount(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        logger.info("Register a new Account: -----> UserDto {}", userDTO.toString());
        User user = userService.createUserInformation(userDTO.getPassword(), userDTO.getUsername(), userDTO.getEmail());
        emailService.sendActivationEmail(user, BASE_URL);
        return new ResponseEntity<>(ResponseGenerator.buildSuccessResponse(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/v1/account",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccountInfo() {
        return userService.getUserByUsername(SecurityUtil.getCurrentUserLogin())
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setUsername(user.getUsername());
                    userDTO.setEmail(user.getEmail());
                    userDTO.setAuthorities(user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));
                    return new ResponseEntity<>(ResponseGenerator.buildSuccessResponse(userDTO), HttpStatus.OK);
                }).orElse(new ResponseEntity<>(ResponseGenerator.buildFailedResponse(ResponseCode.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/v1/account/password_reset/{resetKey}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@PathVariable(value = "resetKey") final String resetKey, @ModelAttribute(value = "newPassword") final String newPassword,Locale local) {
        logger.info("Start to request password_reset . resetKey: {}", resetKey);
        ResponseDTO successResponse = ResponseGenerator.buildSuccessResponse(messageSource.getMessage("account.passwordReset.success",null,local));
        ResponseDTO failedResponse = ResponseGenerator.buildSuccessResponse(messageSource.getMessage("account.passwordReset.failed",null,local));
        return userService.resetPassword(newPassword,resetKey)
                .map(user -> {
                    return new ResponseEntity<>(successResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(failedResponse,HttpStatus.BAD_REQUEST));

    }

    @RequestMapping(value = "/v1/account/password_reset",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> requestPasswordReset(@ModelAttribute(value = "needResetEmail") String needResetEmail, Locale local) {
        logger.info("Start to request password_reset . needResetEmail: {}", needResetEmail);
        ResponseDTO successResponse = ResponseGenerator.buildSuccessResponse(messageSource.getMessage("account.passwordResetRequest.success",null,local));
        ResponseDTO failedResponse = ResponseGenerator.buildSuccessResponse(messageSource.getMessage("account.passwordResetRequest.failed",null,local));
        return userService.requestPasswordReset(needResetEmail)
                .map(user -> {
                    emailService.sendPasswordResetMail(user, BASE_URL);
                    logger.info("Request SUCCESS: {}" + successResponse.getMessage());
                    return new ResponseEntity<>(successResponse,HttpStatus.CREATED);
                })
                .orElse(new ResponseEntity<>(failedResponse,HttpStatus.BAD_REQUEST));
    }


    @RequestMapping(value = "/v1/account/existence",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkIfExistUser(@RequestParam(value = "login", required = false, defaultValue = "") final String login,
                                              @RequestParam(value = "email", required = false, defaultValue = "") final String email) {
        Map<String,Boolean> result = new HashMap<>();
        result.put("existed",userService.checkIfExitUserActivatedByLoginOrEmail(login, email));
        return new ResponseEntity<>(ResponseGenerator.buildSuccessResponse(result),HttpStatus.OK);
    }
}
