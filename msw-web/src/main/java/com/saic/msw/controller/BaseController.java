package com.saic.msw.controller;

import com.saic.msw.dto.ApiReqDto;
import com.saic.msw.dto.ApiResultDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zzz
 * @CREATE_DATE: 2021/7/23 18:03
 * @UPDATE_DATE：2021/7/23 18:03
 * @Version 1.0
 * @Description：
 */
@RestController
@RequestMapping("user")
public class BaseController {

    /**
     * 保存修改公用
     * @param reqDto 修改或保存的对象
     * @return ApiResultDto
     */
    @RequestMapping("login")
    public ApiResultDto login(@RequestBody ApiReqDto reqDto) {
        reqDto.getUserLoginDto().getAccount();
        reqDto.getUserLoginDto().getPassword();
        //验证账号
        //生成token,并存放用户信息放入缓存
        return ApiResultDto.success();
    }
}
