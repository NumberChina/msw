package com.saic.msw.service;

import com.saic.msw.model.TUser;
import com.saic.msw.mapper.TUserMapper;
import com.saic.msw.api.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzz
 * @since 2021-07-21
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {


    @Override
    public TUser findById(Integer id) {
       return baseMapper.selectById(id);
    }
}
