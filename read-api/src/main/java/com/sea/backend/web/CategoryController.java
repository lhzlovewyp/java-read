package com.sea.backend.web;
import com.sea.common.core.Result;
import com.sea.common.core.ResultGenerator;
import com.sea.backend.model.Category;
import com.sea.backend.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sea.model.dto.ResultDTO;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by lvhaizhen on 2018/09/17.
*/
@RestController
@RequestMapping("/backend/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResultDTO add(Category category) {
        categoryService.save(category);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public ResultDTO delete(@RequestParam Integer id) {
        categoryService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public ResultDTO update(Category category) {
        categoryService.update(category);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public ResultDTO detail(@RequestParam Integer id) {
        Category category = categoryService.findById(id);
        return ResultGenerator.genSuccessResult(category);
    }

    @PostMapping("/list")
    public ResultDTO list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,Category category) {
        PageHelper.startPage(page, size);

        Condition condition = new Condition(Category.class);
        Example.Criteria criteria = condition.createCriteria();

        List<Category> list = categoryService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
