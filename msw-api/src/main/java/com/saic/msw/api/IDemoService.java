package com.saic.msw.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.saic.msw.dto.ApiReqDto;
import com.saic.msw.model.Demo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 * Demo 服务类
 * </p>
 *
 * @author zzz
 * @since 2021-07-22
 */
public interface IDemoService extends IService<Demo> {

    Page<Demo> queryPage(ApiReqDto reqDto);
}

