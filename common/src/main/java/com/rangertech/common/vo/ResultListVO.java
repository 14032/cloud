
package com.rangertech.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rangertech.common.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ResultListVO<T> implements Serializable {

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
    private List<T> data;

    public ResultListVO() {
    }

    public ResultListVO(Integer code, String msg) {
        this.retcode = code;
        this.msg = msg;
    }

    private ResultListVO(Integer code, String msg, List<T> data) {
        this.retcode = code;
        this.msg = msg;
        this.data = data;
    }


    public static <T> ResultListVO<T> ok() {
        return ok(new ArrayList<>());
    }

    public static <T> ResultListVO<T> ok(Integer code, String msg) {
        return build(code, msg, new ArrayList<>());
    }

    public static <T> ResultListVO<T> ok(List<T> data) {
        return build(ResultEnum.SUCCESS, data);
    }

    public static <T> ResultListVO<T> err(ResultEnum resultEnum) {
        return build(resultEnum, new ArrayList<>());
    }

    public static <T> ResultListVO<T> err() {
        return build(ResultEnum.SERVER_ERROR, new ArrayList<>());
    }

    public static <T> ResultListVO<T> err(ResultEnum resultEnum, String message) {
        return err(resultEnum.getCode(), message);
    }

    public static <T> ResultListVO<T> err(String message) {
        return err(ResultEnum.SERVER_ERROR.getCode(), message);
    }

    public static <T> ResultListVO<T> err(ResultEnum resultEnum, List<T> data) {
        return build(resultEnum.getCode(), resultEnum.getMessage(), data);
    }

    private static <T> ResultListVO<T> err(Integer code, String message) {
        if (code == null) {
            return err(message);
        }
        return build(code, message, new ArrayList<>());
    }

    private static <T> ResultListVO<T> build(ResultEnum resultEnum, List<T> data) {
        return build(resultEnum.getCode(), resultEnum.getMessage(), data);
    }

    private static <T> ResultListVO<T> build(Integer code, String message, List<T> data) {
        return new ResultListVO<>(code, message, data);
    }

    @JsonIgnore
    public boolean isOk() {
        return ResultEnum.SUCCESS.getCode().equals(this.retcode);
    }

}