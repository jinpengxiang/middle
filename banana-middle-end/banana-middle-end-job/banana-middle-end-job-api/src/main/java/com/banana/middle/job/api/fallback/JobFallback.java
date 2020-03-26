package com.banana.middle.job.api.fallback;

import com.banana.common.StatusCode;
import com.banana.middle.job.api.IJobAPI;
import com.banana.job.entity.bo.XxlJobInfoEditParam;
import com.banana.job.entity.bo.XxlJobInfoPageParam;
import com.banana.job.entity.bo.XxlJobInfoParam;
import com.banana.job.entity.bo.XxlJobInfoVO;
import com.banana.starter.entity.BaseResponseBody;
import org.springframework.stereotype.Component;

@Component
public class JobFallback implements IJobAPI {

    @Override
    public BaseResponseBody<XxlJobInfoVO> pageList(XxlJobInfoPageParam params) {
        return BaseResponseBody.from(StatusCode.FEIGN_FAILED);
    }

    @Override
    public BaseResponseBody<String> add(XxlJobInfoParam params) {
        return BaseResponseBody.from(StatusCode.FEIGN_FAILED);
    }

    @Override
    public BaseResponseBody<String> update(XxlJobInfoEditParam params) {
        return BaseResponseBody.from(StatusCode.FEIGN_FAILED);
    }

    @Override
    public BaseResponseBody<String> remove(int id) {
        return BaseResponseBody.from(StatusCode.FEIGN_FAILED);
    }

    @Override
    public BaseResponseBody<String> pause(int id) {
        return BaseResponseBody.from(StatusCode.FEIGN_FAILED);
    }

    @Override
    public BaseResponseBody<String> start(int id) {
        return BaseResponseBody.from(StatusCode.FEIGN_FAILED);
    }

    @Override
    public BaseResponseBody<String> triggerJob(int id, String executorParam) {
        return BaseResponseBody.from(StatusCode.FEIGN_FAILED);
    }
}
