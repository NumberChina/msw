package com.saic.msw.dto;

import com.saic.msw.enums.ResponseEnum;
import lombok.Data;

/**
 * @author: zzz
 * @date 2021/7/20 22:13
 * @description： 响应实体类
 * @version: 1.0
 */
@Data
public class ApiResultDto<T> {
    private Integer code;
    private String msg;
    private T data;

    public static ApiResultDto success(){
        ApiResultDto resultDto = new ApiResultDto();
        resultDto.code = ResponseEnum.SUCCESS.getCode();
        resultDto.msg = ResponseEnum.SUCCESS.getMsg();
       return new ApiResultDto();
    }

    public static <T> ApiResultDto success(T data){
        ApiResultDto resultDto = new ApiResultDto();
        resultDto.code = ResponseEnum.SUCCESS.getCode();
        resultDto.msg = ResponseEnum.SUCCESS.getMsg();
        resultDto.data = data;
        return new ApiResultDto();
    }

    public static ApiResultDto error(){
        ApiResultDto resultDto = new ApiResultDto();
        resultDto.code = ResponseEnum.ERROR.getCode();
        resultDto.msg = ResponseEnum.ERROR.getMsg();
        return new ApiResultDto();
    }

    public static ApiResultDto error(String msg){
        ApiResultDto resultDto = new ApiResultDto();
        resultDto.code = ResponseEnum.ERROR.getCode();
        resultDto.msg = msg;
        return new ApiResultDto();
    }
}
