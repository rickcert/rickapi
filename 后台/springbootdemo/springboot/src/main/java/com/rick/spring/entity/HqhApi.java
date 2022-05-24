package com.rick.spring.entity;

import java.io.Serializable;

/**
 * (HqhApi)实体类
 *
 * @author makejava
 * @since 2022-05-18 19:20:27
 */
public class HqhApi extends  HqhBaseEntity implements Serializable {
    private static final long serialVersionUID = 618779350851555749L;

    private Integer id;

    private String apiName;

    private String description;

    private String method;

    private String parameters;

    private String example;

    private Integer calltimes;

    private String status;

    private String exampleurl;

    private String author;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Integer getCalltimes() {
        return calltimes;
    }

    public void setCalltimes(Integer calltimes) {
        this.calltimes = calltimes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExampleurl() {
        return exampleurl;
    }

    public void setExampleurl(String exampleurl) {
        this.exampleurl = exampleurl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}

