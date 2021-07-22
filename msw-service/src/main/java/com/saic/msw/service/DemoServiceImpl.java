package com.saic.msw.service;

import com.saic.msw.model.Demo;
import com.saic.msw.mapper.DemoMapper;
import com.saic.msw.dto.ApiReqDto;
import com.saic.msw.api.IDemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * <p>
 * Demo 服务实现类
 * </p>
 *
 * @author zzz
 * @since 2021-07-22
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements IDemoService {

   @Override
   public Page<Demo> queryPage(ApiReqDto reqDto) {
       Page<Demo> pageParam = new Page<>(reqDto.getPageDto().getPageNum(), reqDto.getPageDto().getPageSize());
       QueryWrapper<Demo> queryWrapper = new QueryWrapper<>();
       Page<Demo> page = baseMapper.selectPage(pageParam,queryWrapper);
       return page;
   }
}