package com.saic.msw.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zzz
 * @CREATE_DATE: 2021/7/22 15:33
 * @UPDATE_DATE：2021/7/22 15:33
 * @Version 1.0
 * @Description：
 */

@Data
public class PageDto implements Serializable {

    private Long pageNum;

    private Long pageSize;

    public Long getPageNum() {
        return null==pageNum?1:pageNum;
    }

    public Long getPageSize() {
        return null==pageSize?10:pageSize;
    }

}
