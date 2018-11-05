package com.sea.backend.service;
import com.sea.backend.model.AdminUser;
import com.sea.common.core.Service;


/**
 * Created by lvhaizhen on 2018/08/17.
 */
public interface AdminUserService extends Service<AdminUser> {

    /**
     * 根据用户名查询用户信息.
     * @param username
     * @return
     */
    AdminUser queryAdminUserByUsername(String username);

}
