package com.banana.job.admin.service;


import com.banana.job.entity.bo.XxlJobInfoVO;
import com.banana.job.entity.po.XxlJobInfo;

import java.util.Date;
import java.util.Map;

/**
 * core job action for xxl-job
 * 
 * @author 金鹏祥 2016-5-28 15:30:33
 */
public interface XxlNewJobService {

	/**
	 * page list
	 *
	 * @param start
	 * @param length
	 * @param jobGroup
	 * @param jobDesc
	 * @param executorHandler
	 * @param author
	 * @return
	 */
	Map<String, Object> pageList(int start, int length, int jobGroup, int triggerStatus, String jobDesc, String executorHandler, String author);

	/**
	 * page list
	 *
	 * @param start
	 * @param length
	 * @param jobGroup
	 * @param jobDesc
	 * @param executorHandler
	 * @param author
	 * @return
	 */
	XxlJobInfoVO pageVOList(int start, int length, int jobGroup, int triggerStatus, String jobDesc, String executorHandler, String author);

	/**
	 * add job
	 *
	 * @param jobInfo
	 * @return
	 */
	String add(XxlJobInfo jobInfo);

	/**
	 * update job
	 *
	 * @param jobInfo
	 * @return
	 */
	String update(XxlJobInfo jobInfo);

	/**
	 * remove job
	 * 	 *
	 * @param id
	 * @return
	 */
	String remove(int id);

	/**
	 * start job
	 *
	 * @param id
	 * @return
	 */
	String start(int id);

	/**
	 * stop job
	 *
	 * @param id
	 * @return
	 */
	String stop(int id);

	/**
	 * dashboard info
	 *
	 * @return
	 */
	Map<String,Object> dashboardInfo();

	/**
	 * chart info
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> chartInfo(Date startDate, Date endDate);

}
