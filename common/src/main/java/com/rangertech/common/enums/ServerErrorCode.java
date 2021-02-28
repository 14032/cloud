package com.rangertech.common.enums;

public enum ServerErrorCode {
    /**
     * 网关内部异常
     */
    INTERNAL_GATEWAY_ERROR(1000, "网关内部异常"),
    /**
     * 身份认证失败
     */
    AUTHENTICATE_FAILED(1002, "身份认证失败"),
    /**
     * 请求已被熔断
     */
    REQUEST_FUSE_ERROR(1003, "请求已被熔断"),
    /**
     * 服务调用失败
     */
    SERVER_CALL_FAILS(1004, "服务调用失败");

    private final int code;
    private final String name;

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    ServerErrorCode(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
