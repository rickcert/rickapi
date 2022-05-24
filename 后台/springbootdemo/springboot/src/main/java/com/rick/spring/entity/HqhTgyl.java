package com.rick.spring.entity;

import java.io.Serializable;

/**
 * (HqhTgyl)实体类
 *
 * @author makejava
 * @since 2022-05-18 15:03:49
 */
public class HqhTgyl extends  HqhBaseEntity implements Serializable {
    private static final long serialVersionUID = -48768968508594887L;

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

