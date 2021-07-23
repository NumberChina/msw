package com.saic.msw.controllerAdvice;

import cn.hutool.core.util.ObjectUtil;
import com.saic.msw.constants.MessageConstants;
import com.saic.msw.dto.ApiResultDto;
import com.saic.msw.error.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Slf4j
@ControllerAdvice
public class ControllerGlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ApiResultDto runtimeExceptionHandle(RuntimeException exception) {
        log.error("请求失败，请稍后再试", exception.getMessage(),exception);
        String msg;
        if(ObjectUtil.isNotNull(exception.getCause())){
            msg = exception.getCause().toString();
        }else{
            msg = exception.getMessage();
        }
        return ApiResultDto.error(msg.length()>150?"请求失败，请稍后再试！":msg);
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResultDto httpMessageNotReadableExceptionHandle(HttpMessageNotReadableException exception) {
        String msg = MessageConstants.PARAMS_MSG;
        log.error(msg, exception.getMessage(),exception);
        return ApiResultDto.error(msg);
    }

    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public ApiResultDto httpMessageNotReadableExceptionHandle(ApiException exception) {
        String msg = exception.getMsg();
        log.error(msg, exception.getMessage(),exception);
        return ApiResultDto.error(exception.getCode(),msg);
    }



    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResultDto handleIllegalArgumentExceptionException(IllegalArgumentException e){
        log.error("请求失败，请稍后再试", e.getMessage(),e);
        String msg = e.getMessage();
        return ApiResultDto.error(msg.length()>150?"请求失败，请稍后再试！":msg);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResultDto exceptionHandle(Exception exception) {
        log.error("请求失败：未知错误", exception.getMessage(),exception);
        return ApiResultDto.error("请求失败：未知错误");
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResultDto methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(),e);
        List<FieldError> errors = e.getBindingResult().getFieldErrors();

        StringBuilder builder = new StringBuilder();
        for (FieldError error : errors) {
            String field = error.getField();
            String message = error.getDefaultMessage();
            builder.append("[").append(field).append("]").append(message).append("; ");
        }
        return ApiResultDto.error( builder.toString());
    }
}
