package com.sea.backend.model.dto;

import com.sea.backend.model.Category;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lvhaizhen on 2018/11/22.
 */
public class CategoryTreeDTO implements Serializable {

    private Integer id;

    private Integer parentId;

    private String parentName;

    private String categoryName;

    private Integer status;

    private List<CategoryTreeDTO> categoryTreeDTOList;

    private Integer hasChildren;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CategoryTreeDTO> getCategoryTreeDTOList() {
        return categoryTreeDTOList;
    }

    public void setCategoryTreeDTOList(List<CategoryTreeDTO> categoryTreeDTOList) {
        this.categoryTreeDTOList = categoryTreeDTOList;
    }

    public Integer getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Integer hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
