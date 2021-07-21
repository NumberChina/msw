package com.saic.msw.controller;


import com.saic.msw.api.IUserService;
import com.saic.msw.dto.ApiReqDto;
import com.saic.msw.dto.ApiResultDto;
import com.saic.msw.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/user")
public class UserController {


    @Resource
    private IUserService iTUserService;

    @RequestMapping("findByUser")
    public ApiResultDto findByiId(@RequestBody ApiReqDto<User> apiReqDto) {
        User tUser =  iTUserService.findById(apiReqDto.getParams().getId());
        return ApiResultDto.success(tUser);
    }

    @RequestMapping("findById")
    public ApiResultDto findById(Integer id) {
        User tUser = iTUserService.findById(id);
        return ApiResultDto.success(tUser);
    }

}

