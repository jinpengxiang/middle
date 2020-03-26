package com.banana.common;

/**
 * 微服务名，对应各个微服务的spring.application.name
 * 服务间消费时，使用下列常量，避免拼写错误，方便后续修改
 */
public interface ServiceNameConst {
    String MESSAGE = "message";
    String JOB_ADMIN = "jobadmin";
}
