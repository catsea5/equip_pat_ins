package com.lyj.equipment.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 全局统一返回结果类
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class Result<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public Result(){}

    protected static <T> com.lyj.equipment.util.Result<T> build(T data) {
        com.lyj.equipment.util.Result<T> util = new com.lyj.equipment.util.Result<T>();
        if (data != null)
            util.setData(data);
        return util;
    }

    public static <T> com.lyj.equipment.util.Result<T> build(T body, ResultCodeEnum utilCodeEnum) {
        com.lyj.equipment.util.Result<T> util = build(body);
        util.setCode(utilCodeEnum.getCode());
        util.setMessage(utilCodeEnum.getMessage());
        return util;
    }

    public static <T> com.lyj.equipment.util.Result<T> build(Integer code, String message) {
        com.lyj.equipment.util.Result<T> util = build(null);
        util.setCode(code);
        util.setMessage(message);
        return util;
    }

    public static<T> com.lyj.equipment.util.Result<T> ok(){
        return com.lyj.equipment.util.Result.ok(null);
    }

    /**
     * 操作成功
     * @param data
     * @param <T>
     * @return
     */
    public static<T> com.lyj.equipment.util.Result<T> ok(T data){
        com.lyj.equipment.util.Result<T> util = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static<T> com.lyj.equipment.util.Result<T> fail(){
        return com.lyj.equipment.util.Result.fail(null);
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static<T> com.lyj.equipment.util.Result<T> fail(T data){
        com.lyj.equipment.util.Result<T> util = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    public com.lyj.equipment.util.Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public com.lyj.equipment.util.Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    public boolean isOk() {
        if(this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue()) {
            return true;
        }
        return false;
    }
}
