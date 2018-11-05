package com.sea.backend.service.impl;

import com.sea.backend.dao.CategoryMapper;
import com.sea.backend.model.Category;
import com.sea.backend.service.CategoryService;
import com.sea.common.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by lvhaizhen on 2018/09/17.
 */
@Service
@Transactional
public class CategoryServiceImpl extends AbstractService<Category> implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

}
