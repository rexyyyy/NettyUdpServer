package com.net.msg.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "成功")
    ,
    ERROR(301, "错误")
    ,
    UNKNOWERROR(302, "未知错误")
    ,
    PARAMETER_ERROR(303, "参数错误")
    ,
    FILE_NOT_EXIST(304, "文件不存在")
    ,
    CLOSE_FAILD(305, "关闭流失败")
    ;

    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
