//package com.xxl.job.admin.core.jobbean;
//
//import JobTriggerPoolHelper;
//import TriggerTypeEnum;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.JobKey;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//
///**
// * http job bean
// * “@DisallowConcurrentExecution” diable concurrent, thread size can not be only one, better given more
// * @author 金鹏祥 2015-12-17 18:20:34
// */
////@DisallowConcurrentExecution
//public class RemoteHttpJobBean extends QuartzJobBean {
//	private static Logger logger = LoggerFactory.getLogger(RemoteHttpJobBean.class);
//
//	@Override
//	protected void executeInternal(JobExecutionContext context)
//			throws JobExecutionException {
//
//		// load jobId
//		JobKey jobKey = context.getTrigger().getJobKey();
//		Integer jobId = Integer.valueOf(jobKey.getName());
//
//
//	}
//
//}