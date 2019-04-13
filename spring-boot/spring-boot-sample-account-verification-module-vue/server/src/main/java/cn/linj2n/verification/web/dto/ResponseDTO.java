package cn.linj2n.verification.web.dto;

import cn.linj2n.verification.web.dto.support.ResponseCode;
import org.springframework.http.HttpStatus;

public class ResponseDTO<T> {
    private int code;
    private String message;
    private T data;

    public ResponseDTO setCode(ResponseCode responseCode) {
        this.code = responseCode.code();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseDTO setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }
}
