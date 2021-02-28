package com.rangertech.common.enums;

public enum ResultEnum {

    SUCCESS(0, "success"),
    FAILED(500, "failed"),
    SERVER_ERROR(1, "common error"),
    PARAM_ERROR(11, "incorrect parameter");

    private final Integer code;
    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
