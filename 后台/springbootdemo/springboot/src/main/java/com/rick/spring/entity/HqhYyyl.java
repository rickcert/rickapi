package com.rick.spring.entity;

import java.io.Serializable;

/**
 * (HqhYyyl)实体类
 *
 * @author makejava
 * @since 2022-05-18 15:03:50
 */
public class HqhYyyl extends HqhBaseEntity implements Serializable {
    private static final long serialVersionUID = 537345106633474155L;

    private Integer id;

    private String content;

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

