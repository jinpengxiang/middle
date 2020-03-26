package com.banana.job.admin.controller;

import com.banana.common.StatusCode;
import com.banana.job.admin.core.exception.XxlJobException;
import com.banana.job.admin.core.route.ExecutorRouteStrategyEnum;
import com.banana.job.admin.core.thread.JobTriggerPoolHelper;
import com.banana.job.admin.core.trigger.TriggerTypeEnum;
import com.banana.job.admin.core.util.I18nUtil;
import com.banana.job.admin.dao.XxlJobGroupDao;
import com.banana.job.admin.service.LoginService;
import com.banana.job.admin.service.XxlNewJobService;
import com.banana.job.entity.bo.XxlJobInfoEditParam;
import com.banana.job.entity.bo.XxlJobInfoPageParam;
import com.banana.job.entity.bo.XxlJobInfoParam;
import com.banana.job.entity.bo.XxlJobInfoVO;
import com.banana.job.entity.po.XxlJobGroup;
import com.banana.job.entity.po.XxlJobInfo;
import com.banana.job.entity.po.XxlJobUser;
import com.banana.starter.entity.BaseResponseBody;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.job.core.glue.GlueTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * index controller
 * @author 金鹏祥 2015-12-19 16:13:16
 */

@Api(tags = "任务")
@RestController
@RequestMapping("/banana/job/")
public class JobInfoNewController {
	@Resource
	private XxlJobGroupDao xxlJobGroupDao;
	@Resource
	private XxlNewJobService xxlNewJobService;

	@RequestMapping
	public String index(HttpServletRequest request, Model model, @RequestParam(required = false, defaultValue = "-1") int jobGroup) {

		// 枚举-字典
		model.addAttribute("ExecutorRouteStrategyEnum", ExecutorRouteStrategyEnum.values());	// 路由策略-列表
		model.addAttribute("GlueTypeEnum", GlueTypeEnum.values());								// Glue类型-字典
		model.addAttribute("ExecutorBlockStrategyEnum", ExecutorBlockStrategyEnum.values());	// 阻塞处理策略-字典

		// 执行器列表
		List<XxlJobGroup> jobGroupList_all =  xxlJobGroupDao.findAll();

		// filter group
		List<XxlJobGroup> jobGroupList = filterJobGroupByRole(request, jobGroupList_all);
		if (jobGroupList==null || jobGroupList.size()==0) {
			throw new XxlJobException(I18nUtil.getString("jobgroup_empty"));
		}

		model.addAttribute("JobGroupList", jobGroupList);
		model.addAttribute("jobGroup", jobGroup);

		return "jobinfo/jobinfo.index";
	}

	public static List<XxlJobGroup> filterJobGroupByRole(HttpServletRequest request, List<XxlJobGroup> jobGroupList_all){
		List<XxlJobGroup> jobGroupList = new ArrayList<>();
		if (jobGroupList_all!=null && jobGroupList_all.size()>0) {
			XxlJobUser loginUser = (XxlJobUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
			if (loginUser.getRole() == 1) {
				jobGroupList = jobGroupList_all;
			} else {
				List<String> groupIdStrs = new ArrayList<>();
				if (loginUser.getPermission()!=null && loginUser.getPermission().trim().length()>0) {
					groupIdStrs = Arrays.asList(loginUser.getPermission().trim().split(","));
				}
				for (XxlJobGroup groupItem:jobGroupList_all) {
					if (groupIdStrs.contains(String.valueOf(groupItem.getId()))) {
						jobGroupList.add(groupItem);
					}
				}
			}
		}
		return jobGroupList;
	}
	public static void validPermission(HttpServletRequest request, int jobGroup) {
		XxlJobUser loginUser = (XxlJobUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
		if (!loginUser.validPermission(jobGroup)) {
			throw new RuntimeException(I18nUtil.getString("system_permission_limit") + "[username="+ loginUser.getUsername() +"]");
		}
	}

	@ApiOperation(value = "获取任务列表")
	@PostMapping("/pageList")
	public BaseResponseBody<XxlJobInfoVO> pageList(@RequestBody XxlJobInfoPageParam params){
		return BaseResponseBody.success(StatusCode.SUCCESS, xxlNewJobService.pageVOList(
				params.getBaseListParam().getSafePage(),
				params.getBaseListParam().getPageSize(),
				params.getJobGroup(),
				params.getTriggerStatus(),
				params.getJobDesc(),
				params.getExecutorHandler(),
				params.getAuthor()));
	}

	@ApiOperation(value = "添加任务")
	@PostMapping("/add")
	public BaseResponseBody<String> add(@RequestBody XxlJobInfoParam params) {
		XxlJobInfo en = new XxlJobInfo();
		BeanUtils.copyProperties(params, en);
		en.setGlueType(GlueTypeEnum.BEAN.getDesc());
		return BaseResponseBody.success(StatusCode.SUCCESS, xxlNewJobService.add(en));
	}

	@ApiOperation(value = "修改任务")
	@PostMapping("/update")
	public BaseResponseBody<String> update(@RequestBody XxlJobInfoEditParam params) {
		XxlJobInfo en = new XxlJobInfo();
		BeanUtils.copyProperties(params, en);
		en.setGlueType(GlueTypeEnum.BEAN.getDesc());
		return BaseResponseBody.success(StatusCode.SUCCESS, xxlNewJobService.update(en));
	}

	@ApiOperation(value = "删除任务")
	@DeleteMapping("/remove/{id}")
	public BaseResponseBody<String> remove(@PathVariable int id) {
		return BaseResponseBody.success(StatusCode.SUCCESS, xxlNewJobService.remove(id));
	}

	@ApiOperation(value = "停止任务")
	@GetMapping("/stop/{id}")
	public BaseResponseBody<String> pause(@PathVariable int id) {
		return BaseResponseBody.success(StatusCode.SUCCESS, xxlNewJobService.stop(id));
	}

	@ApiOperation(value = "开始任务")
	@GetMapping("/start/{id}")
	public BaseResponseBody<String> start(@PathVariable int id) {
		return BaseResponseBody.success(StatusCode.SUCCESS, xxlNewJobService.start(id));
	}

	@ApiOperation(value = "触发任务")
	@GetMapping("/trigger/{id}/{executorParam}")
	//@PermissionLimit(limit = false)
	public BaseResponseBody<String> triggerJob(@PathVariable int id, @PathVariable String executorParam) {
		// force cover job param
		if (executorParam == null) {
			executorParam = "";
		}

		JobTriggerPoolHelper.trigger(id, TriggerTypeEnum.MANUAL, -1, null, executorParam);
		return BaseResponseBody.success(StatusCode.SUCCESS, String.valueOf(ReturnT.SUCCESS.getCode()));
	}
}
