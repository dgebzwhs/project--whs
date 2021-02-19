package com.translate.ozh2zh.constant;

/**
 * @author zyj
 * @version 1.0
 * @date 2020/5/6 0006 下午 4:55
 */
public enum CustomHttpStatus {
    REQUEST_SUCCESS(0,"请求成功"),
    PARAM_CHECK(40001, "参数校验失败"),
    TOKEN_INVALID(50001, "token验证失败"),
    SERVICE_ABNORMAL(50021, "业务逻辑发生异常"),
    ;

    CustomHttpStatus(int code, String explain) {
        this.code = code;
        this.explain = explain;
    }

    private int code;
    private String explain;

    public int getCode() {
        return this.code;
    }
}
