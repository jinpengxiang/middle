package com.banana.job.entity.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class BaseListParam {
    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;

    @Min(1)
    @ApiModelProperty(value = "分页参数，从1开始，默认1", example = "1")
    protected Integer page;

    @Min(1)
    @ApiModelProperty(value = "分页参数，最小1，默认10", example = "10")
    protected Integer pageSize;

    public BaseListParam() {
    }

    public BaseListParam(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    @ApiModelProperty(hidden = true)
    public int getSafePageSize() {
        return pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
    }

    @ApiModelProperty(hidden = true)
    public int getSafePage() {
        return page == null ? DEFAULT_PAGE : page;
    }
}
