package com.banana.middle.job.api;

import com.banana.common.ServiceNameConst;
import com.banana.job.entity.bo.XxlJobInfoEditParam;
import com.banana.job.entity.bo.XxlJobInfoPageParam;
import com.banana.job.entity.bo.XxlJobInfoParam;
import com.banana.job.entity.bo.XxlJobInfoVO;
import com.banana.middle.job.api.fallback.ActuatorFallback;
import com.banana.starter.entity.BaseResponseBody;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = ServiceNameConst.JOB_ADMIN, fallback = ActuatorFallback.class)
public interface IJobAPI {
    @ApiOperation(value = "获取任务列表")
    @PostMapping("/banana/job/pageList")
    BaseResponseBody<XxlJobInfoVO> pageList(@RequestBody XxlJobInfoPageParam params);

    @ApiOperation(value = "添加任务")
    @PostMapping("/banana/job/add")
    BaseResponseBody<String> add(@RequestBody XxlJobInfoParam params);

    @ApiOperation(value = "修改任务")
    @PostMapping("/banana/job/update")
    BaseResponseBody<String> update(@RequestBody XxlJobInfoEditParam params);

    @ApiOperation(value = "删除任务")
    @DeleteMapping("/banana/job/remove/{id}")
    BaseResponseBody<String> remove(@PathVariable("id") int id);

    @ApiOperation(value = "停止任务")
    @GetMapping("/banana/job/stop/{id}")
    BaseResponseBody<String> pause(@PathVariable("id") int id);

    @ApiOperation(value = "开始任务")
    @GetMapping("/banana/job/start/{id}")
    BaseResponseBody<String> start(@PathVariable("id") int id);

    @ApiOperation(value = "触发任务")
    @GetMapping("/banana/job/trigger/{id}/{executorParam}")
    BaseResponseBody<String> triggerJob(@PathVariable("id") int id, @PathVariable("executorParam") String executorParam);
}

