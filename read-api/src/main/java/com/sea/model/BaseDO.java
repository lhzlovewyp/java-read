package com.sea.model;

import java.io.Serializable;

/**
 * Created by lvhaizhen on 2018/7/25.
 */
public class BaseDO implements Serializable {

    /** 起始页 */
    private int startPage = 1;

    /** 每页记录数 */
    private int pageSize = 20;

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStart() {
        if (startPage < 0 || pageSize < 0) {
            return 0;
        } else {
            return ((startPage - 1) * pageSize);
        }
    }

}
