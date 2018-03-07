package com.ronrong.thymeleaf.mat.entity.standard;

import java.io.Serializable;
import java.util.Date;

public class StandardProduct implements Serializable {
    private static final long serialVersionUID = 4666747714676000014L;

    private Long id;

    /**
     * 名称:产品名称
     */
    private String name;

    /**
     * 名称:简称
     */
    private String alias;

    /**
     * 名称:产品code
     */
    private String code;

    /**
     * 名称:产品简介
     */
    private String description;

    /**
     * 名称:列表图片地址
     */
    private String imgUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}