package cn.linj2n.verification.web.dto.support;

public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS(20000),//成功
    /**
     * agree
     */
    FAIL(40000),//失败
    /**
     * agree
     */
    UNAUTHORIZED(40001),
    /**
     * agree
     */
    NOT_FOUND(40004),//接口不存在
    /**
     * 服务器错误
     */
    INTERNAL_SERVER_ERROR(50000);

    private final int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
