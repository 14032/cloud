package com.rangertech.gateway.exception;

import com.rangertech.common.enums.ResultEnum;
import com.rangertech.common.enums.ServerErrorCode;

public class BizException extends RuntimeException {
    private Integer code;
    private String message;
    private Object data;

    public BizException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.message = resultEnum.getMessage();
        this.code = resultEnum.getCode();
    }

    public BizException(ServerErrorCode serverErrorCode) {
        super(serverErrorCode.getName());
        this.message = serverErrorCode.getName();
        this.code = serverErrorCode.getCode();
    }

    public BizException(ServerErrorCode serverErrorCode, String message) {
        this.message = message;
        this.code = serverErrorCode.getCode();
    }

    public BizException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }
}
