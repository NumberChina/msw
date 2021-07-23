package com.saic.msw.dto;

import lombok.Data;

/**
 * @author: zzz
 * @date 2021/7/20 22:45
 * @description：
 * @version: 1.0
 */
@Data
public class UserLoginDto {

    //账号
    private String account;
    //密码
    private String password;

}
