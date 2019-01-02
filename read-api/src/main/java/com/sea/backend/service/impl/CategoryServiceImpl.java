package com.sea.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.sea.backend.dao.CategoryMapper;
import com.sea.backend.model.Category;
import com.sea.backend.service.CategoryService;
import com.sea.common.core.AbstractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by lvhaizhen on 2018/09/17.
 */
@Service
@Transactional
public class CategoryServiceImpl extends AbstractService<Category> implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findByCondition(Category category) {

        Condition condition = new Condition(Category.class);
        Example.Criteria criteria = condition.createCriteria();
        if(category.getParentId() != null){
            criteria.andEqualTo("parentId",category.getParentId());
        }
        if(category.getId() != null){
            criteria.andEqualTo("id",category.getId());
        }
        if(StringUtils.isNoneBlank(category.getCategoryName())){
            criteria.andEqualTo("categoryName",category.getCategoryName());
        }
        return this.findByCondition(condition);
    }

    @Override
    public List<Category> findChildrenByParentId(Integer parentId) {

        Category category = new Category();
        category.setParentId(parentId);
        return findByCondition(category);
    }
}
