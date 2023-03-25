package com.lyj.equipment.util;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    PARAM_ERROR( 202, "参数不正确"),
    SERVICE_ERROR(203, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    DATA_UPDATE_ERROR(205, "数据版本异常"),

    LOGIN_AUTH(208, "未登陆"),
    PERMISSION(209, "没有权限"),

    CODE_ERROR(210, "验证码错误"),
    LOGIN_MOBLE_ERROR(211, "账号不正确"),
    PASSWORD_ERROR(212,"密码错误"),
    LOGIN_MOBLE_EXIST(213,"账号已存在"),
    LOGIN_MOBLE_APPLY(214,"账号审核中"),
    EQUIP_STATE_ZERO(215,"设备未启用，无权进行操作"),
    EQUIP_NOT_MATE(216,"你无权获取该设备")
    ;

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
