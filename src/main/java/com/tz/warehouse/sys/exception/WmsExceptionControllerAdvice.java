package com.tz.warehouse.sys.exception;

import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.common.utils.RRException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理Created by TangZhen on 2022/11/19
 */
@RestControllerAdvice(basePackages = {"com.tz.warehouse.sys.controller", "com.tz.warehouse.bus.controller"})
public class WmsExceptionControllerAdvice {
    @ExceptionHandler(value = RRException.class)
    public R handleValidException(RRException e){
        return R.error(e.getMessage());
    }
}
