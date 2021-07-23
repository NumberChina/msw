package com.saic.msw.error;

import lombok.Data;

/**
 * @Author: zzz
 * @CREATE_DATE: 2021/7/23 15:04
 * @UPDATE_DATE：2021/7/23 15:04
 * @Version 1.0
 * @Description：
 */
@Data
public class ApiException extends RuntimeException {

    private Integer code;

    private String msg;

    public ApiException(Integer code,String msg){
       this.code = code;
       this.msg = msg;
    }

}
