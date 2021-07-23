package com.saic.msw.controller;

import com.saic.msw.annotation.NoLogin;
import com.saic.msw.api.IDemoService;
import com.saic.msw.model.Demo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.saic.msw.dto.ApiReqDto;
import com.saic.msw.dto.ApiResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  Demo前端控制器  RestController注解 将结果以JSON形式返回
 * </p>
 *
 * @author leilei
 * @since 2021-07-22
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    public IDemoService demoService;

    /**
     * 保存修改公用
     * @param reqDto 修改或保存的对象
     * @return ApiResultDto
     */
    @RequestMapping("/save")
    public ApiResultDto save(@RequestBody ApiReqDto<Demo> reqDto) {
        demoService.updateById(reqDto.getParams());
        return ApiResultDto.success();
    }

    /**批量删除 支持POST GET
     * @param reqDto Long 类型 List 集合
     * @return ApiResultDto
     */
    @RequestMapping("remove")
    public ApiResultDto delete(@RequestBody ApiReqDto<List<Long>> reqDto) {
        demoService.removeByIds(reqDto.getParams());
        return ApiResultDto.success();
    }

    /**
     * 查询一个
     *
     * @param reqDto 查询id
     * @return ApiResultDto
     */
    @RequestMapping("findOne")
    @NoLogin
    public ApiResultDto findOne(@RequestBody ApiReqDto<Long> reqDto) {
        Demo demo = demoService.getById(reqDto.getParams());
        return ApiResultDto.success(demo);
    }

    /**
     * 查询所有
     * @param
     * @return
     */
    @RequestMapping("queryList")
    @NoLogin
    public ApiResultDto queryList() {
        List<Demo> page = demoService.list();
        return ApiResultDto.success(page);
    }


    /** 分页查询
     * @param reqDto
     * @return ApiResultDto
     */
    @RequestMapping("queryPage")
    public ApiResultDto findAll(@RequestBody ApiReqDto<Demo> reqDto) {
        Page<Demo> page = demoService.queryPage(reqDto);
        return ApiResultDto.success(page);
    }
}
