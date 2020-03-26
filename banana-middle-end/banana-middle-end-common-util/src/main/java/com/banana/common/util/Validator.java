package com.banana.common.util;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
/**
 * 校验器：利用正则表达式校验邮箱、手机号等
 * 
 * @author liwq
 * @date 2019年6月26日 下午3:57:30
 */
public class Validator {
	
	//正则表达式：验证用户名  //^[\u4e00-\u9fa5]{0,}$
	public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
	// 中文、英文、数字包括下划线以及英文句点（长度在2-49之间）
	public static final String REGEX_USERNAME_SEC = "^[\u4E00-\u9FA5A-Za-z0-9_.]{1,49}$";
	// 字母、数字、下划线长度在6~20之间
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]\\w{5,19}$";//
	// 正则表达式：验证手机验证码
	public static final String REGEX_CODE = "^[0-9]{6}$";
	//国内手机号校验以1开头的11位数字
	public static final String REGEX_MOBILE_CN = "^1\\d{10}$";

	
	/**
	 * 校验用户名（中文、数字、英文、下划线、英文句点、长度在2-255之间）
	 * @author liwq 
	 * @date 2019年6月26日 下午3:58:37 
	 * @param userName 输入参数
	 * @return true 校验通过   false 校验失败
	 *
	 */
	public static boolean isUserName(String userName) {
	    if (StringUtils.isEmpty(userName)) {
                return false;
            }
	    return Pattern.matches(Validator.REGEX_USERNAME_SEC, userName);
	}
	/**
	 * 验证手机验证码
	 * @author liwq 
	 * @date 2019年6月26日 下午4:55:37 
	 * @param userName 输入参数
	 * @return true 校验通过   false 校验失败
	 *
	 */
	public static boolean isCode(String code) {
		if (StringUtils.isEmpty(code)) {
			return false;
		}
		return Pattern.matches(REGEX_CODE, code);
	}
	
	public static boolean isMobile(String mobile) {
		return Pattern.matches(REGEX_MOBILE_CN, mobile);
	}
}
