package cn.linj2n.verification.web.utils;

import cn.linj2n.verification.web.dto.ResponseDTO;
import cn.linj2n.verification.web.dto.support.ResponseCode;
import org.springframework.http.HttpStatus;

public class ResponseGenerator {

    private static final String DEFAULT_SUCCESS_STATUS = "success";
    private static final String DEFAULT_FAILED_STATUS = "fail";

//    public static ResponseDTO buildHttpResponse(HttpStatus status){
//        return new ResponseDTO()
//                .setCode(status.getReasonPhrase())
//                .setMessage(status.getReasonPhrase());
//    }

    public static ResponseDTO buildSuccessResponse() {
        return new ResponseDTO()
                .setCode(ResponseCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_STATUS);
    }

//    public static ResponseDTO buildSuccessResponse(String message) {
//        return new ResponseDTO()
//                .setCode(DEFAULT_SUCCESS_STATUS)
//                .setMessage(message);
//    }

    public static <T> ResponseDTO<T> buildSuccessResponse(String message, T data) {
        return new ResponseDTO()
                .setCode(ResponseCode.SUCCESS)
                .setMessage(message)
                .setData(data);
    }

    public static <T> ResponseDTO<T> buildSuccessResponse(T data) {
        return new ResponseDTO()
                .setCode(ResponseCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_STATUS)
                .setData(data);
    }

    public static ResponseDTO buildFailedResponse() {
        return new ResponseDTO()
                .setCode(ResponseCode.FAIL)
                .setMessage(DEFAULT_FAILED_STATUS);
    }

    public static ResponseDTO buildFailedResponse(String message) {
        return new ResponseDTO()
                .setCode(ResponseCode.FAIL)
                .setMessage(message);
    }

    public static <T> ResponseDTO<T> buildFailedResponse(String message, T data) {
        return new ResponseDTO()
                .setCode(ResponseCode.FAIL)
                .setMessage(message)
                .setData(data);
    }

    public static <T> ResponseDTO<T> buildFailedResponse(T data) {
        return new ResponseDTO()
                .setCode(ResponseCode.FAIL)
                .setData(data);
    }

}