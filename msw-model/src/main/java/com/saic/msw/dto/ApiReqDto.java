package com.saic.msw.dto;

import lombok.Data;

/**
 * @author: zzz
 * @date 2021/7/20 22:13
 * @description：请求实体类
 * @version: 1.0
 */
@Data
public class ApiReqDto<T> {

    private T params;

    private UserLoginDto userLoginDto;

}
