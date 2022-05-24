package com.rick.spring.entity;

import java.io.Serializable;

/**
 * (HqhBz)实体类
 *
 * @author makejava
 * @since 2022-05-18 15:03:45
 */
public class HqhBz  extends HqhBaseEntity implements Serializable {
    private static final long serialVersionUID = -14430057939609132L;

    private Integer id;

    private String content;

    private String width;

    private String height;

    private String classification;

    private String delFlag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}

