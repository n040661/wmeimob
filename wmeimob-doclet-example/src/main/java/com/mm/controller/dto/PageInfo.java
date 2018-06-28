package com.mm.controller.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象测试
 * @param <T>
 * @author zJun
 * @date 2018年6月28日 上午10:54:48
 */
public class PageInfo<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    /** 当前页 */
    private int pageNum;
    /** 总记录数 */
    private long total;
    /** 总页数 */
    private int pages;
    /** 结果集 */
    private List<T> list;

    public PageInfo() {
    }

    /**
     * 包装Page对象
     * @param list
     */
    public PageInfo(List<T> list) {
            this.pageNum = 1;
            this.pages = list.size() / 10;
            this.list = list;
            this.total = list.size();
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
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

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
