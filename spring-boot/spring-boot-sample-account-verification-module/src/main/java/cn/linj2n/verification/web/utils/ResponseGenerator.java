package cn.linj2n.verification.web.utils;

import cn.linj2n.verification.web.dto.ResponseDto;
import org.springframework.http.HttpStatus;

public class ResponseGenerator {

    public static final String DEFAULT_SUCCESS_STATUS = "success";
    public static final String DEFAULT_FAILED_STATUS = "fail";

    public static ResponseDto buildHttpResponse(HttpStatus status){
        return new ResponseDto()
                .setStatus(status.getReasonPhrase())
                .setMessage(status.getReasonPhrase());
    }

    public static ResponseDto buildSuccessResponse() {
        return new ResponseDto()
                .setStatus(DEFAULT_SUCCESS_STATUS);
    }

    public static ResponseDto buildSuccessResponse(String message) {
        return new ResponseDto()
                .setStatus(DEFAULT_SUCCESS_STATUS)
                .setMessage(message);
    }

    public static <T> ResponseDto<T> buildSuccessResponse(String message, T data) {
        return new ResponseDto()
                .setStatus(DEFAULT_SUCCESS_STATUS)
                .setMessage(message)
                .setData(data);
    }

    public static <T> ResponseDto<T> buildSuccessResponse(T data) {
        return new ResponseDto()
                .setStatus(DEFAULT_SUCCESS_STATUS)
                .setData(data);
    }

    public static ResponseDto buildFailedResponse() {
        return new ResponseDto()
                .setStatus(DEFAULT_FAILED_STATUS);
    }

    public static ResponseDto buildFailedResponse(String message) {
        return new ResponseDto()
                .setStatus(DEFAULT_FAILED_STATUS)
                .setMessage(message);
    }

    public static <T> ResponseDto<T> buildFailedResponse(String message, T data) {
        return new ResponseDto()
                .setStatus(DEFAULT_FAILED_STATUS)
                .setMessage(message)
                .setData(data);
    }

    public static <T> ResponseDto<T> buildFailedResponse(T data) {
        return new ResponseDto()
                .setStatus(DEFAULT_FAILED_STATUS)
                .setData(data);
    }

}