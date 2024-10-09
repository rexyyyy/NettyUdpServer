package com.net.msg.util;

import com.net.msg.enums.ResultCodeEnum;
import com.net.msg.vo.ResultVo;

public class ResultUtil {
    public static ResultVo success() {
        return success(null);
    }
    public static ResultVo success(Object object) {
        ResultVo result = new ResultVo();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg("成功");
        result.setData(object);
        return result;
    }
    public static ResultVo success(Integer code, Object object) {
        return success(code, null, object);
    }
    public static ResultVo success(Integer code, String msg, Object object) {
        ResultVo result = new ResultVo();

        result.setCode(code);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }
    public static ResultVo error( String msg) {
        ResultVo result = new ResultVo();
        result.setCode(ResultCodeEnum.ERROR.getCode());
        result.setMsg(msg);
        return result;
    }
    public static ResultVo error(Integer code, String msg) {
        ResultVo result = new ResultVo();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
