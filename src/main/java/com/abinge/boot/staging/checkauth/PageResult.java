/*
 * Copyright (c) 2001-2020 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.abinge.boot.staging.checkauth;

import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author chenbj
 * @version 1.0.0
 * @time 2021/4/30 10:35
 */
@Data
public class PageResult<T> extends BaseResult{
    /**
     * 对象
     */
    private List<T> rows;
    /**
     * 总记录数
     */
    private int total;
    /**
     * 总页数
     */
    protected int totalPage;
    /**
     * 一页大小
     */
    protected int pageSize;
    /**
     * 当前页数，从 0开始，0代表第一页
     */
    protected int page;
    protected int startPos;
    protected int endPos;

}
