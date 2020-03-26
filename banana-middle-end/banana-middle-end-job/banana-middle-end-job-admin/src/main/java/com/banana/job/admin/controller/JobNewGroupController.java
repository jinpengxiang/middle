package com.banana.job.admin.controller;

import com.banana.common.StatusCode;
import com.banana.job.admin.core.util.I18nUtil;
import com.banana.job.admin.dao.XxlJobGroupDao;
import com.banana.job.admin.dao.XxlJobInfoDao;
import com.banana.job.admin.dao.XxlJobRegistryDao;
import com.banana.job.entity.bo.XxlJobGroupEditParam;
import com.banana.job.entity.bo.XxlJobGroupParam;
import com.banana.job.entity.po.XxlJobGroup;
import com.banana.job.entity.po.XxlJobRegistry;
import com.banana.starter.entity.BaseResponseBody;
import com.banana.starter.exception.BusinessException;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.enums.RegistryConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * job group controller
 * @author 金鹏祥 2016-10-02 20:52:56
 */
@Api(tags = "执行器")
@RestController
@RequestMapping("/banana/jobGroup/")
public class JobNewGroupController {

	@Resource
	public XxlJobInfoDao xxlJobInfoDao;
	@Resource
	public XxlJobGroupDao xxlJobGroupDao;
	@Resource
	private XxlJobRegistryDao xxlJobRegistryDao;

	@RequestMapping
	public String index(Model model) {

		// job group (executor)
		List<XxlJobGroup> list = xxlJobGroupDao.findAll();

		model.addAttribute("list", list);
		return "jobgroup/jobgroup.index";
	}

	@ApiOperation(value = "添加执行器")
	@PostMapping("/save")
	public BaseResponseBody<String> save(@RequestBody XxlJobGroupParam params){
		XxlJobGroup xxlJobGroup = new XxlJobGroup();
		BeanUtils.copyProperties(params, xxlJobGroup);

		// valid
		if (xxlJobGroup.getAppName()==null || xxlJobGroup.getAppName().trim().length()==0) {
			throw new BusinessException(StatusCode.ERROR, I18nUtil.getString("system_please_input"));
//			return new ReturnT<String>(500, (I18nUtil.getString("system_please_input")+"AppName") );
		}
		if (xxlJobGroup.getAppName().length()<4 || xxlJobGroup.getAppName().length()>64) {
			throw new BusinessException(StatusCode.ERROR, I18nUtil.getString("jobgroup_field_appName_length"));
//			return new ReturnT<String>(500, I18nUtil.getString("jobgroup_field_appName_length") );
		}
		if (xxlJobGroup.getTitle()==null || xxlJobGroup.getTitle().trim().length()==0) {
			throw new BusinessException(StatusCode.ERROR, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobgroup_field_title")));
//			return new ReturnT<String>(500, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobgroup_field_title")) );
		}
		if (xxlJobGroup.getAddressType()!=0) {
			if (xxlJobGroup.getAddressList()==null || xxlJobGroup.getAddressList().trim().length()==0) {
				throw new BusinessException(StatusCode.ERROR, I18nUtil.getString("jobgroup_field_addressType_limit"));
//				return new ReturnT<String>(500, I18nUtil.getString("jobgroup_field_addressType_limit") );
			}
			String[] addresss = xxlJobGroup.getAddressList().split(",");
			for (String item: addresss) {
				if (item==null || item.trim().length()==0) {
					throw new BusinessException(StatusCode.ERROR, I18nUtil.getString("jobgroup_field_registryList_unvalid"));
//					return new ReturnT<String>(500, I18nUtil.getString("jobgroup_field_registryList_unvalid") );
				}
			}
		}

		int ret = xxlJobGroupDao.save(xxlJobGroup);
//		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
		return (ret>0)?BaseResponseBody.success(StatusCode.SUCCESS, String.valueOf(xxlJobGroup.getId())):BaseResponseBody.error(StatusCode.ERROR);
	}

	@ApiOperation(value = "修改执行器")
	@PostMapping("/update")
	public BaseResponseBody<String> update(@RequestBody XxlJobGroupEditParam params){
		XxlJobGroup xxlJobGroup = new XxlJobGroup();
		BeanUtils.copyProperties(params, xxlJobGroup);

		// valid
		if (xxlJobGroup.getAppName()==null || xxlJobGroup.getAppName().trim().length()==0) {
			throw new BusinessException(StatusCode.ERROR, (I18nUtil.getString("system_please_input")+"AppName"));
//			return new ReturnT<String>(500, (I18nUtil.getString("system_please_input")+"AppName") );
		}
		if (xxlJobGroup.getAppName().length()<4 || xxlJobGroup.getAppName().length()>64) {
			throw new BusinessException(StatusCode.ERROR, I18nUtil.getString("jobgroup_field_appName_length"));
//			return new ReturnT<String>(500, I18nUtil.getString("jobgroup_field_appName_length") );
		}
		if (xxlJobGroup.getTitle()==null || xxlJobGroup.getTitle().trim().length()==0) {
			throw new BusinessException(StatusCode.ERROR, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobgroup_field_title")));
//			return new ReturnT<String>(500, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobgroup_field_title")) );
		}
		if (xxlJobGroup.getAddressType() == 0) {
			// 0=自动注册
			List<String> registryList = findRegistryByAppName(xxlJobGroup.getAppName());
			String addressListStr = null;
			if (registryList!=null && !registryList.isEmpty()) {
				Collections.sort(registryList);
				addressListStr = "";
				for (String item:registryList) {
					addressListStr += item + ",";
				}
				addressListStr = addressListStr.substring(0, addressListStr.length()-1);
			}
			xxlJobGroup.setAddressList(addressListStr);
		} else {
			// 1=手动录入
			if (xxlJobGroup.getAddressList()==null || xxlJobGroup.getAddressList().trim().length()==0) {
				throw new BusinessException(StatusCode.ERROR, I18nUtil.getString("jobgroup_field_addressType_limit"));
//				return new ReturnT<String>(500, I18nUtil.getString("jobgroup_field_addressType_limit") );
			}
			String[] addresss = xxlJobGroup.getAddressList().split(",");
			for (String item: addresss) {
				if (item==null || item.trim().length()==0) {
					throw new BusinessException(StatusCode.ERROR, I18nUtil.getString("jobgroup_field_registryList_unvalid"));
//					return new ReturnT<String>(500, I18nUtil.getString("jobgroup_field_registryList_unvalid") );
				}
			}
		}

		int ret = xxlJobGroupDao.update(xxlJobGroup);
//		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
		return (ret>0)?BaseResponseBody.success(StatusCode.SUCCESS, String.valueOf(ReturnT.SUCCESS.getCode())):BaseResponseBody.error(StatusCode.ERROR);
	}

	private List<String> findRegistryByAppName(String appNameParam){
		HashMap<String, List<String>> appAddressMap = new HashMap<String, List<String>>();
		List<XxlJobRegistry> list = xxlJobRegistryDao.findAll(RegistryConfig.DEAD_TIMEOUT);
		if (list != null) {
			for (XxlJobRegistry item: list) {
				if (RegistryConfig.RegistType.EXECUTOR.name().equals(item.getRegistryGroup())) {
					String appName = item.getRegistryKey();
					List<String> registryList = appAddressMap.get(appName);
					if (registryList == null) {
						registryList = new ArrayList<String>();
					}

					if (!registryList.contains(item.getRegistryValue())) {
						registryList.add(item.getRegistryValue());
					}
					appAddressMap.put(appName, registryList);
				}
			}
		}
		return appAddressMap.get(appNameParam);
	}

	@ApiOperation(value = "删除执行器")
	@DeleteMapping("/remove/{id}")
	public BaseResponseBody<String> remove(@PathVariable int id){

		// valid
		int count = xxlJobInfoDao.pageListCount(0, 10, id, -1,  null, null, null);
		if (count > 0) {
			throw new BusinessException(StatusCode.ERROR, I18nUtil.getString("jobgroup_del_limit_0"));
//			return new ReturnT<String>(500, I18nUtil.getString("jobgroup_del_limit_0") );
		}

		List<XxlJobGroup> allList = xxlJobGroupDao.findAll();
		if (allList.size() == 1) {
			throw new BusinessException(StatusCode.ERROR, I18nUtil.getString("jobgroup_del_limit_1"));
//			return new ReturnT<String>(500, I18nUtil.getString("jobgroup_del_limit_1") );
		}

		int ret = xxlJobGroupDao.remove(id);
//		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
		return (ret>0)?BaseResponseBody.success(StatusCode.SUCCESS, String.valueOf(ReturnT.SUCCESS.getCode())):BaseResponseBody.error(StatusCode.ERROR);
	}

	@ApiOperation(value = "获取执行器")
	@GetMapping("/loadById/{id}")
	public BaseResponseBody<XxlJobGroup> loadById(@PathVariable int id){
		XxlJobGroup jobGroup = xxlJobGroupDao.load(id);
		if(ObjectUtils.isEmpty(jobGroup)){
//			throw new BusinessException(StatusCode.RESOURCE_NOT_EXIST, I18nUtil.getString(StatusCode.RESOURCE_NOT_EXIST.getCode()));
			return BaseResponseBody.from(StatusCode.RESOURCE_NOT_EXIST, I18nUtil.getString(StatusCode.RESOURCE_NOT_EXIST.getCode()));
		}
		return BaseResponseBody.success(StatusCode.SUCCESS, jobGroup);
//		return jobGroup!=null?new ReturnT<XxlJobGroup>(jobGroup):new ReturnT<XxlJobGroup>(ReturnT.FAIL_CODE, null);
	}

}
