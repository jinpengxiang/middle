package com.banana.job.admin.controller.resolver;

import com.banana.common.StatusCode;
import com.banana.job.admin.core.exception.XxlJobException;
import com.banana.starter.entity.BaseResponseBody;
import com.xxl.job.core.biz.model.ReturnT;
import com.banana.job.admin.core.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * common exception resolver
 *
 * @author 金鹏祥 2016-1-6 19:22:18
 */
@Component
public class WebExceptionResolver implements HandlerExceptionResolver {
	private static transient Logger logger = LoggerFactory.getLogger(WebExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		if (!(ex instanceof XxlJobException)) {
			logger.error("WebExceptionResolver:{}", ex);
		}

		// if json
		boolean isJson = false;
		HandlerMethod method = (HandlerMethod)handler;
		ResponseBody responseBody = method.getMethodAnnotation(ResponseBody.class);
		if (responseBody != null) {
			isJson = true;
		}

		// error result
		ReturnT<String> errorResult = new ReturnT<String>(ReturnT.FAIL_CODE, ex.toString().replaceAll("\n", "<br/>"));
		// response
		ModelAndView mv = new ModelAndView();

		if(request.getRequestURL().indexOf("/banana/job/") != -1 || request.getRequestURL().indexOf("/banana/jobGroup/") != -1){
			BaseResponseBody<String> returnBody = new BaseResponseBody<>();
			returnBody.setMessage(ex.getMessage());
			returnBody.setStatus(StatusCode.ERROR.getCode());
			returnBody.setData(null);

			response.setContentType("application/json;charset=utf-8");
			try {
				response.getWriter().print(JacksonUtil.writeValueAsString(returnBody));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mv;
		}

		if (isJson) {
			try {
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(JacksonUtil.writeValueAsString(errorResult));
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			return mv;
		} else {

			mv.addObject("exceptionMsg", errorResult.getMsg());
			mv.setViewName("/common/common.exception");
			return mv;
		}
	}
	
}