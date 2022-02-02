package com.remo.article.pojo.common;

public class PageInfoProperties {
    
    /**
     * 当前页码，默认页码为第一页
     */
    public Long currentPage = 0L;

    /**
     * 每页数据数量，默认查询500条
     */
    public Long pageSize = 500L;

    /**
     * 数据总数
     */
    public Long totalRecord;

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }

    
}
