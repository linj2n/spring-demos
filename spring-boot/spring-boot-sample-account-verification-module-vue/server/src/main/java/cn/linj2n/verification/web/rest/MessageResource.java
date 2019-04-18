package cn.linj2n.verification.web.rest;

import cn.linj2n.verification.web.utils.ResponseGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageResource {
    @RequestMapping(value = "/v1/message",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMessage() {
        return new ResponseEntity<>(ResponseGenerator.buildSuccessResponse("Welcome"), HttpStatus.OK);
    }
}
