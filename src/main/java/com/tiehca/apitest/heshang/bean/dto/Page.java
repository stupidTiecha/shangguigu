package com.tiehca.apitest.heshang.bean.dto;

import java.util.List;

/**
 * @author chen9
 */
public class Page<T> {
    private long total;
    private int pages;
    private int pageNum;
    private int pageSize;
    private List<T> list;

    public Page(long total, int pageNum, int pageSize, List<T> list) {
        this.total = total;
        this.pages = (int) (total / pageSize + 1);
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
