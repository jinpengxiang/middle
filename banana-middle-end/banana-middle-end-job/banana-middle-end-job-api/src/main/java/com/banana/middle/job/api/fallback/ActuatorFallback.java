package com.banana.middle.job.api.fallback;

import com.banana.common.StatusCode;
import com.banana.middle.job.api.IActuatorAPI;
import com.banana.job.entity.bo.XxlJobGroupEditParam;
import com.banana.job.entity.bo.XxlJobGroupParam;
import com.banana.job.entity.po.XxlJobGroup;
import com.banana.starter.entity.BaseResponseBody;
import org.springframework.stereotype.Component;

@Component
public class ActuatorFallback implements IActuatorAPI {

    @Override
    public BaseResponseBody<String> save(XxlJobGroupParam params) {
        return BaseResponseBody.from(StatusCode.FEIGN_FAILED);
    }

    @Override
    public BaseResponseBody<String> update(XxlJobGroupEditParam params) {
        return BaseResponseBody.from(StatusCode.FEIGN_FAILED);
    }

    @Override
    public BaseResponseBody<String> remove(int id) {
        return BaseResponseBody.from(StatusCode.FEIGN_FAILED);
    }

    @Override
    public BaseResponseBody<XxlJobGroup> loadById(int id) {
        return BaseResponseBody.from(StatusCode.FEIGN_FAILED);
    }
}
