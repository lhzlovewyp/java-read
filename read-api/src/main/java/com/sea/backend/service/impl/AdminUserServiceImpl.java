package com.sea.backend.service.impl;

import com.sea.backend.dao.AdminUserMapper;
import com.sea.backend.model.AdminUser;
import com.sea.backend.service.AdminUserService;
import com.sea.common.core.AbstractService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by lvhaizhen on 2018/08/17.
 */
@Service
@Transactional
public class AdminUserServiceImpl extends AbstractService<AdminUser> implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;


    public AdminUser queryAdminUserByUsername(String username){
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(username);
        List<AdminUser> adminUserList = adminUserMapper.select(adminUser);
        return CollectionUtils.isNotEmpty(adminUserList)? adminUserList.get(0):null;
    }

}
