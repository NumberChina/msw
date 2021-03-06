package com.saic.msw.dto;

import com.saic.msw.enums.ResponseEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zzz
 * @date 2021/7/20 22:13
 * @description： 响应实体类
 * @version: 1.0
 */
@Data
public class ApiResultDto<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public static ApiResultDto success(){
        ApiResultDto resultDto = new ApiResultDto();
        resultDto.code = ResponseEnum.SUCCESS.getCode();
        resultDto.msg = ResponseEnum.SUCCESS.getMsg();
       return resultDto;
    }

    public static <T> ApiResultDto success(T data){
        ApiResultDto resultDto = new ApiResultDto();
        resultDto.code = ResponseEnum.SUCCESS.getCode();
        resultDto.msg = ResponseEnum.SUCCESS.getMsg();
        resultDto.data = data;
        return resultDto;
    }

    public static ApiResultDto error(){
        ApiResultDto resultDto = new ApiResultDto();
        resultDto.code = ResponseEnum.ERROR.getCode();
        resultDto.msg = ResponseEnum.ERROR.getMsg();
        return resultDto;
    }

    public static ApiResultDto error(String msg){
        ApiResultDto resultDto = new ApiResultDto();
        resultDto.code = ResponseEnum.ERROR.getCode();
        resultDto.msg = msg;
        return  resultDto;
    }

    public static ApiResultDto error(Integer code,String msg){
        ApiResultDto resultDto = new ApiResultDto();
        resultDto.code = code;
        resultDto.msg = msg;
        return  resultDto;
    }
}
