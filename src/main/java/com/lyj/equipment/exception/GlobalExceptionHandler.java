package com.lyj.equipment.exception;

import com.lyj.equipment.util.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) //统一异常
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(EquipException.class) //自定义异常，需要在异常代码块手动抛出throw new EquipException(massage,code);
    public Result error(EquipException e){
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }
}
