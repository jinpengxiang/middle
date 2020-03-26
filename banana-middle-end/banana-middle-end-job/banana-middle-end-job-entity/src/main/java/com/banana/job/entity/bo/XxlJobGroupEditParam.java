package com.banana.job.entity.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 金鹏祥 on 16/9/30.
 */
@Data
public class XxlJobGroupEditParam {
    @ApiModelProperty(value = "执行器ID")
    private int id;

    @ApiModelProperty(value = "执行器应用名称(后期自定义组件引入对应的appName)")
    private String appName;

    @ApiModelProperty(value = "执行器名称")
    private String title;

    @ApiModelProperty(value = "排序序号；UI作用")
    private int order;

    @ApiModelProperty(value = "执行器地址类型：0=自动注册、1=手动录入；自动注册：执行器服务接入调度中心默认30秒广播")
    private int addressType;

    @ApiModelProperty(value = "执行器地址列表，多地址逗号分隔(手动录入)；集群使用；单机可不填")
    private String addressList;

    @ApiModelProperty(value = "执行器地址列表(系统注册);集群使用；单机可不填")
    private List<String> registryList;
    public List<String> getRegistryList() {
        if (addressList!=null && addressList.trim().length()>0) {
            registryList = new ArrayList<String>(Arrays.asList(addressList.split(",")));
        }
        return registryList;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getAddressType() {
        return addressType;
    }

    public void setAddressType(int addressType) {
        this.addressType = addressType;
    }

    public String getAddressList() {
        return addressList;
    }

    public void setAddressList(String addressList) {
        this.addressList = addressList;
    }

}
