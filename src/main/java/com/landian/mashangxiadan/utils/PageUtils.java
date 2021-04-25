package com.landian.mashangxiadan.utils;

import com.github.pagehelper.PageInfo;

/**
 * @author Yu W
 * @date 2020/7/16 11:45
 */
public class PageUtils {
    /**
     * 将分页信息封装到统一的接口
     * @param pageRequest
     * @param pageInfo
     * @return
     */
    public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
