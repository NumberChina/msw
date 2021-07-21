package com.saic.msw.service;

import com.saic.msw.api.IDemoService;
import com.saic.msw.dto.DemoDto;
import com.saic.msw.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Author: zzz
 * @CREATE_DATE: 2021/7/21 14:17
 * @UPDATE_DATE：2021/7/21 14:17
 * @Version 1.0
 * @Description：
 */
@Service
public class DemoService implements IDemoService {

    @Resource
    private DemoMapper demoMapper;

    @Override
    public void save(DemoDto demoDto) {
        demoMapper.save();
    }
}
