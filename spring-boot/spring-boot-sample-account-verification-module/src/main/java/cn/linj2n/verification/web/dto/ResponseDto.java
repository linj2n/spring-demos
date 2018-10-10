package cn.linj2n.verification.web.dto;

import org.springframework.http.HttpStatus;

public class ResponseDto<T> {
    private String status;
    private String message;
    private T data;


    public String getStatus() {
        return status;
    }

    public ResponseDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseDto setData(T data) {
        this.data = data;
        return this;
    }
}
