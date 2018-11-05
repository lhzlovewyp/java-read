package com.sea.backend.web;
import com.sea.common.core.Result;
import com.sea.common.core.ResultGenerator;
import com.sea.backend.model.AdminUser;
import com.sea.backend.service.AdminUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sea.common.utils.DesUtils;
import com.sea.model.dto.ResultDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

/**
* Created by lvhaizhen on 2018/08/17.
*/
@RestController
@RequestMapping("/backend/adminUser")
public class AdminUserController {
    @Resource
    private AdminUserService adminUserService;

    @PostMapping("/add")
    public ResultDTO add(AdminUser adminUser) {

        if(StringUtils.isBlank(adminUser.getUsername())){
            return ResultGenerator.genFailResult("用户名不能为空");
        }
        if(StringUtils.isBlank(adminUser.getPassword())){
            return ResultGenerator.genFailResult("密码不能为空");
        }
        AdminUser user = adminUserService.queryAdminUserByUsername(adminUser.getUsername());
        if(user !=null){
            return ResultGenerator.genFailResult("已存在的用户名");
        }

        adminUser.setPassword(DesUtils.getInstance().encrypt(adminUser.getPassword()));
        adminUserService.save(adminUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public ResultDTO delete(@RequestParam Integer id) {
        adminUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/updateStatus")
    public ResultDTO updateStatus(@RequestParam Integer id,@RequestParam Byte status) {
        if(status == 0){
            status = 1;
        }else if(status == 1){
            status  = 0;
        }
        AdminUser adminUser = new AdminUser();
        adminUser.setId(id);
        adminUser.setStatus(status);
        adminUserService.update(adminUser);
        return ResultGenerator.genSuccessResult();
    }


    @PostMapping("/update")
    public ResultDTO update(AdminUser adminUser) {

        AdminUser temp = adminUserService.findById(adminUser.getId());
        if(!temp.getPassword().equals(adminUser.getPassword())){
            adminUser.setPassword(DesUtils.getInstance().encrypt(adminUser.getPassword()));
        }
        adminUserService.update(adminUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public ResultDTO detail(@RequestParam Integer id) {
        AdminUser adminUser = adminUserService.findById(id);
        return ResultGenerator.genSuccessResult(adminUser);
    }

    @PostMapping("/list")
    public ResultDTO list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size,AdminUser adminUser) {
        PageHelper.startPage(page, size);
        //List<AdminUser> list = adminUserService.findAll();

        Condition condition = new Condition(AdminUser.class);
        Example.Criteria criteria = condition.createCriteria();
        if(StringUtils.isNoneBlank(adminUser.getUsername())){
            criteria.andEqualTo("username",adminUser.getUsername());
        }
        List<AdminUser> list = adminUserService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
