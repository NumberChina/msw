package com.saic.msw.api;

import com.saic.msw.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzz
 * @since 2021-07-21
 */
public interface IUserService extends IService<User> {

    User findById(Integer id);

}
