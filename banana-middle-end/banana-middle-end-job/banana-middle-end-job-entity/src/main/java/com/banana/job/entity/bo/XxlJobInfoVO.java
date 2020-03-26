package com.banana.job.entity.bo;

import com.banana.job.entity.po.XxlJobInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class XxlJobInfoVO {
    @ApiModelProperty(value = "总记录数")
    private int recordsTotal;
//    @ApiModelProperty(value = "过滤后的总记录数")
//    private int recordsFiltered;
    private List<XxlJobInfo> data;
}
