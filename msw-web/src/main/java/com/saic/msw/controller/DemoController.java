package com.saic.msw.controller;

import com.saic.msw.dto.ApiReqDto;
import com.saic.msw.dto.ApiResultDto;
import com.saic.msw.dto.DemoDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zzz
 * @date 2021/7/20 22:06
 * @description：
 * @version: 1.0
 */
@RestController
@RequestMapping("demo")
public class DemoController {


    @RequestMapping("test")
    public ApiResultDto test(@RequestBody ApiReqDto<DemoDto> apiReqDto) {

        return ApiResultDto.success(apiReqDto.getParams());
    }

}
