package com.rick.spring.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * 操作日志(HqhOperLog)实体类
 *
 * @author makejava
 * @since 2022-05-20 16:47:02
 */
public class HqhOperLog extends HqhBaseEntity implements Serializable {
    private static final long serialVersionUID = -17890315111457735L;
    /**
     * 日志主键
     */
    private Integer operId;
    /**
     * 操作模块
     */
    private String title;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * api方法
     */
    private String method;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 操作人员
     */
    private String operName;
    /**
     * 请求url
     */
    private String operUrl;
    /**
     * 操作地址
     */
    private String operIp;
    /**
     * 操作状态
     */
    private Integer status;
    /**
     * 错误消息
     */
    private String errormsg;
    /**
     * 操作时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date opertime;


    public Integer getOperId() {
        return operId;
    }

    public void setOperId(Integer operId) {
        this.operId = operId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public Date getOpertime() {
        return opertime;
    }

    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }

}

