package cn.linj2n.verification.web.utils;

import cn.linj2n.verification.web.dto.JsonResult;
import cn.linj2n.verification.web.enums.ResultCode;

public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static JsonResult genSuccessResult() {
        return new JsonResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> JsonResult<T> genSuccessResult(T data) {
        return new JsonResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static JsonResult genFailResult(String message) {
        return new JsonResult()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

}
