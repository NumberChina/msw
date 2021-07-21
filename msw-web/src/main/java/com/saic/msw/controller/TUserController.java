package com.saic.msw.controller;


import com.saic.msw.api.ITUserService;
import com.saic.msw.dto.ApiReqDto;
import com.saic.msw.dto.ApiResultDto;
import com.saic.msw.dto.DemoDto;
import com.saic.msw.model.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzz
 * @since 2021-07-21
 */
@RestController
@RequestMapping("/tUser")
public class TUserController {

    @Resource
    private ITUserService iTUserService;

    @RequestMapping("findByUser")
    public ApiResultDto findByiId(@RequestBody ApiReqDto<TUser> apiReqDto) {
        TUser tUser =  iTUserService.findById(apiReqDto.getParams().getId());
        return ApiResultDto.success(tUser);
    }

    @RequestMapping("findById")
    public ApiResultDto findById(Integer id) {
        TUser tUser = iTUserService.findById(id);
        return ApiResultDto.success(tUser);
    }

}

