package com.net.msg.exception;

import com.net.msg.enums.ResultCodeEnum;
import lombok.Getter;

@Getter
public class ParameterValidateExcepiton extends RuntimeException{
    // 错误码
    private Integer code;
    // 错误消息
    private String msg;

    public ParameterValidateExcepiton() {
        this(ResultCodeEnum.PARAMETER_ERROR.getCode(), ResultCodeEnum.PARAMETER_ERROR.getMessage());
    }
    public ParameterValidateExcepiton(String msg) {
        this(ResultCodeEnum.PARAMETER_ERROR.getCode(), msg);
    }
    public ParameterValidateExcepiton(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }


}
