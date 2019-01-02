package com.sea.backend.service;
import com.sea.backend.model.Category;
import com.sea.common.core.Service;

import java.util.List;


/**
 * Created by lvhaizhen on 2018/09/17.
 */
public interface CategoryService extends Service<Category> {


    List<Category> findByCondition(Category category);

    List<Category> findChildrenByParentId(Integer parentId);
}
