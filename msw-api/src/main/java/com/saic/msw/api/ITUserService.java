package com.saic.msw.api;

import com.saic.msw.model.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzz
 * @since 2021-07-21
 */
public interface ITUserService extends IService<TUser> {

   TUser findById(Integer id);

}
