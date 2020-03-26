package com.banana.job.admin;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author 金鹏祥 2018-10-28 00:38:13
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ServletComponentScan
public class JobAdminApplication {
	public static void main(String[] args) {
        SpringApplication.run(JobAdminApplication.class, args);
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return new DruidDataSource();
	}

	@Bean
	@ConditionalOnMissingBean(FilterRegistrationBean.class)
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		//TODO 过滤文件类型
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		//TODO 监控单个url调用的sql列表
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		return filterRegistrationBean;
	}

	@Bean
//    @ConditionalOnMissingBean(ServletRegistrationBean.class)
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		//TODO 配置用户名
		reg.addInitParameter("loginUsername", "admin");
		//TODO 配置密码
		reg.addInitParameter("loginPassword", "admin");
		//TODO 在日志中打印执行慢的sql语句
		reg.addInitParameter("logSlowSql", "true");
		//TODO 另外还可配置黑白名单等信息，可参考druid官网介绍
		return reg;
	}
}