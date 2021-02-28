package com.rangertech.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rangertech.common.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 8960474786737581150L;

    /**
     * 错误码
     */
    private Integer retcode;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体内容
     */
    private T data;

    public ResultVO() {
    }

    public ResultVO(Integer code, String msg) {
        this.retcode = code;
        this.msg = msg;
    }

    public ResultVO(Integer code, String msg, Object data) {
        this.retcode = code;
        this.msg = msg;
        this.data = (T) data;
    }

    public ResultVO(ResultEnum resultEnum, Object data) {
        this.retcode = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
        this.data = (T) data;
    }

    public static <E> ResultVO<E> ok() {
        return ok(null);
    }

    public static <E> ResultVO<E> ok(E data) {
        return build(ResultEnum.SUCCESS, data);
    }

    public static <E> ResultVO<E> ok(Integer code, String message) {
        if (code == null) {
            return build(ResultEnum.SUCCESS.getCode(), message, null);
        }
        return build(code, message, null);
    }

    public static <E> ResultVO<E> ok(Integer code, String message, E data) {
        if (code == null) {
            return build(ResultEnum.SUCCESS.getCode(), message, data);
        }
        return build(code, message, data);
    }

    public static <E> ResultVO<E> err() {
        return build(ResultEnum.SERVER_ERROR, null);
    }

    public static <E> ResultVO<E> err(ResultEnum resultEnum) {
        return err(resultEnum.getCode(), resultEnum.getMessage());
    }

    public static <E> ResultVO<E> err(ResultEnum resultEnum, String message) {
        return err(resultEnum.getCode(), message);
    }

    public static <E> ResultVO<E> err(ResultEnum resultEnum, E data) {
        return build(resultEnum.getCode(), resultEnum.getMessage(), data);
    }

    public static <E> ResultVO<E> err(String message) {
        return err(ResultEnum.SERVER_ERROR.getCode(), message);
    }

    public static <E> ResultVO<E> err(Integer code, String message) {
        if (code == null) {
            return err(message);
        }
        return build(code, message, null);
    }

    public static <E> ResultVO<E> err(Integer code, String message, E data) {
        if (code == null) {
            return err(message);
        }
        return build(code, message, data);
    }

    public static <E> ResultVO<E> build(ResultEnum code) {
        return build(code, null);
    }

    private static <T> ResultVO<T> build(ResultEnum resultEnum, T data) {
        return build(resultEnum.getCode(), resultEnum.getMessage(), data);
    }

    private static <T> ResultVO<T> build(Integer code, String message, T data) {
        return new ResultVO<>(code, message, data);
    }

    @JsonIgnore
    public boolean isOk() {
        return ResultEnum.SUCCESS.getCode().equals(this.retcode);
    }

}