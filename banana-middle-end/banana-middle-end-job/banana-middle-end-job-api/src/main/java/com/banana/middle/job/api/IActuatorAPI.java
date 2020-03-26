package com.banana.middle.job.api;

import com.banana.common.ServiceNameConst;
import com.banana.job.entity.bo.XxlJobGroupEditParam;
import com.banana.job.entity.bo.XxlJobGroupParam;
import com.banana.job.entity.po.XxlJobGroup;
import com.banana.middle.job.api.fallback.JobFallback;
import com.banana.starter.entity.BaseResponseBody;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = ServiceNameConst.JOB_ADMIN, fallback = JobFallback.class)
public interface IActuatorAPI {
    @ApiOperation(value = "添加执行器")
    @PostMapping("/banana/jobGroup/save")
    BaseResponseBody<String> save(@RequestBody XxlJobGroupParam params);

    @ApiOperation(value = "修改执行器")
    @PostMapping("/banana/jobGroup/update")
    BaseResponseBody<String> update(@RequestBody XxlJobGroupEditParam params);

    @ApiOperation(value = "删除执行器")
    @DeleteMapping("/banana/jobGroup/remove/{id}")
    BaseResponseBody<String> remove(@PathVariable("id") int id);

    @ApiOperation(value = "获取执行器")
    @GetMapping("/banana/jobGroup/loadById/{id}")
    BaseResponseBody<XxlJobGroup> loadById(@PathVariable("id") int id);
}

