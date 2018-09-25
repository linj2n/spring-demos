package cn.linj2n.verification.web.dto;

import cn.linj2n.verification.web.enums.ResultCode;

public class JsonResult<T> {
    private Integer code;
    private String message;
    private T data;


    public Integer getCode() {
        return code;
    }

    public JsonResult setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public JsonResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public JsonResult setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
