package com.sea.backend.web;
import com.sea.backend.model.dto.CategoryTreeDTO;
import com.sea.common.core.Result;
import com.sea.common.core.ResultGenerator;
import com.sea.backend.model.Category;
import com.sea.backend.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sea.model.dto.ResultDTO;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        List<Category> list = categoryService.findByCondition(category);

        List<CategoryTreeDTO> resultList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(list)){
            for(Category category1:list){
                CategoryTreeDTO categoryTreeDTO = new CategoryTreeDTO();
                categoryTreeDTO.setId(category1.getId());
                categoryTreeDTO.setParentId(category1.getParentId());

                if(category1.getParentId() > 0 ){
                    Category parent = categoryService.findById(category1.getParentId());
                    if(parent != null){
                        categoryTreeDTO.setParentName(parent.getCategoryName());
                    }
                }

                categoryTreeDTO.setCategoryName(category1.getCategoryName());
                categoryTreeDTO.setStatus(category1.getStatus());
                List<Category> children = categoryService.findChildrenByParentId(category1.getId());
                if(CollectionUtils.isNotEmpty(children)){
                    categoryTreeDTO.setHasChildren(1);
                }else{
                    categoryTreeDTO.setHasChildren(0);
                }
                resultList.add(categoryTreeDTO);
            }
        }

        PageInfo pageInfo = new PageInfo(resultList);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
