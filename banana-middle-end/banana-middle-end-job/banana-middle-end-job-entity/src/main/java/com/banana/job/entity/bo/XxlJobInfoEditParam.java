package com.banana.job.entity.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class XxlJobInfoEditParam {
    @ApiModelProperty(value = "任务ID")
    private int id;
    @ApiModelProperty(value = "执行器ID")
    private int jobGroup;
    @ApiModelProperty(value = "任务执行CRON表达式")
    private String jobCron;
    @ApiModelProperty(value = "任务描述")
    private String jobDesc;

//    @ApiModelProperty(value = "添加时间")
//    private Date addTime;
//    @ApiModelProperty(value = "修改时间")
//    private Date updateTime;

    @ApiModelProperty(value = "负责人")
    private String author;
//    @ApiModelProperty(value = "报警邮件")
//    private String alarmEmail;

    @ApiModelProperty(value = "执行器路由策略；默认：NULL=FIRST:第一个；（" +
            "<option value=\"FIRST\" >FIRST:第一个</option>\n" +
            "                                <option value=\"LAST\" >LAST:最后一个</option>\n" +
            "                                <option value=\"ROUND\" >ROUND:轮询</option>\n" +
            "                                <option value=\"RANDOM\" >RANDOM:随机</option>\n" +
            "                                <option value=\"CONSISTENT_HASH\" >CONSISTENT_HASH:一致性HASH</option>\n" +
            "                                <option value=\"LEAST_FREQUENTLY_USED\" >LEAST_FREQUENTLY_USED:最不经常使用</option>\n" +
            "                                <option value=\"LEAST_RECENTLY_USED\" >LEAST_RECENTLY_USED:最近最久未使用</option>\n" +
            "                                <option value=\"FAILOVER\" >FAILOVER:故障转移</option>\n" +
            "                                <option value=\"BUSYOVER\" >BUSYOVER:忙碌转移</option>\n" +
            "                                <option value=\"SHARDING_BROADCAST\" >SHARDING_BROADCAST:分片广播</option>" +
            "）")
    private String executorRouteStrategy;

    @ApiModelProperty(value = "执行器，任务Handler名称")
    private String executorHandler;

    @ApiModelProperty(value = "执行器，任务参数")
    private String executorParam;

    @ApiModelProperty(value = "阻塞处理策略；默认：NULL=FIRST:单机串行；（" +
            "<option value=\"SERIAL_EXECUTION\" >SERIAL_EXECUTION:单机串行</option>\n" +
            "                                    <option value=\"DISCARD_LATER\" >DISCARD_LATER:丢弃后续调度</option>\n" +
            "                                    <option value=\"COVER_EARLY\" >COVER_EARLY:覆盖之前调度</option>" +
            "）")
    private String executorBlockStrategy;

    @ApiModelProperty(value = "任务执行超时时间，单位秒；大于0生效；")
    private int executorTimeout;

    @ApiModelProperty(value = "失败重试次数；大于0生效；")
    private int executorFailRetryCount;

//    @ApiModelProperty(value = "GLUE类型 #com.xxl.job.core.glue.GlueTypeEnum")
//    private String glueType;

//    @ApiModelProperty(value = "GLUE源代码")
//    private String glueSource;
//
//    @ApiModelProperty(value = "GLUE备注")
//    private String glueRemark;
//
//    @ApiModelProperty(value = "GLUE更新时间")
//    private Date glueUpdatetime;

//    @ApiModelProperty(value = "子任务ID，多个逗号分隔")
//    private String childJobId;

//    @ApiModelProperty(value = "调度状态：0-停止，1-运行；")
//    private int triggerStatus;

//    @ApiModelProperty(value = "上次调度时间")
//    private long triggerLastTime;
//
//    @ApiModelProperty(value = "下次调度时间")
//    private long triggerNextTime;
}
