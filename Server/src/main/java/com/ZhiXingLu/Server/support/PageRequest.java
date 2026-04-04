package com.ZhiXingLu.Server.support;

import lombok.Data;

/**
 * @author closer
 * @Description: 分页请求
 * @Create: 2026/4/4 21:30
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private int current = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认降序）
     */
    private String sortOrder = SortOrderCode.SORT_ORDER_DESC;
}
