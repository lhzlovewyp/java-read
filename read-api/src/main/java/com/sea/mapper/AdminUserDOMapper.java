package com.sea.mapper;

import com.sea.model.AdminUserDO;

public interface AdminUserDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUserDO record);

    int insertSelective(AdminUserDO record);

    AdminUserDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUserDO record);

    int updateByPrimaryKey(AdminUserDO record);
}