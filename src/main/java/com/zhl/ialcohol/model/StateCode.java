package com.zhl.ialcohol.model;

/**
 * Description:
 * Author: ZhangHL
 * Version: 1.0
 * Create Date Time: 2024/7/8 15:39.
 */
public enum StateCode {

    // 在此做了个规定,请求成功返回的JsonResult可以使用自定义code,请求错误统一返回1,但是提示信息可以不同
    REQUEST_SUCCESS(0,"请求成功"),
    REQUEST_ERROR(1,"请求失败")
    ;

    private Integer code;
    private String message;

    StateCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
