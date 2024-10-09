package com.net.msg.config;

import com.net.msg.enums.ResultCodeEnum;
import com.net.msg.exception.ParameterValidateExcepiton;
import com.net.msg.util.ResultUtil;
import com.net.msg.vo.ResultVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    // 处理文件为空的异常
    @ExceptionHandler(ParameterValidateExcepiton.class)
    public ResultVo<String> fileExceptionHandler(ParameterValidateExcepiton excepiton) {
        return ResultUtil.error(ResultCodeEnum.PARAMETER_ERROR.getCode(), excepiton.getMsg());
    }

    // 文件不存在异常
    @ExceptionHandler(FileNotFoundException.class)
    public ResultVo<String> fileNotFoundExceptionHandler(FileNotFoundException exception) {
        return ResultUtil.error(ResultCodeEnum.FILE_NOT_EXIST.getCode(), exception.getMessage());
    }

}
