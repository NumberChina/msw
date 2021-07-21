package com.saic.msw.service;

import com.saic.msw.model.User;
import com.saic.msw.mapper.UserMapper;
import com.saic.msw.api.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User findById(Integer id) {
        return baseMapper.selectById(id);
    }

}
