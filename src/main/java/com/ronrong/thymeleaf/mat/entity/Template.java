package com.ronrong.thymeleaf.mat.entity;

import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;
import com.ronrong.thymeleaf.mat.resourceresolver.ResourceType;

/**
 * @Description:模板相关
 * @author:rongshaolin
 */
public class Template {

    /**
     * 资源类型
     */
    private final ResourceType resourceType;
    /**
     * 模板位置
     */
    private final String position;
    /**
     * 装修风格
     */
    private DecorationStyle decorationStyle;
    /**
     * 装修工类型
     */
    private CommonEnum.DecoratorTypeEnum decoratorTypeEnum;

    private boolean cacheData;
    private boolean cacheContent;


    /**
     *
     * @param decorationStyle 装修风格
     * @param resourceType 资源类别
     * @param position 模板位置
     * @param decoratorTypeEnum 装修工类型
     */
    public Template(DecorationStyle decorationStyle, ResourceType resourceType, String position, CommonEnum.DecoratorTypeEnum decoratorTypeEnum, boolean cacheData, boolean cacheContent) {

        this.decorationStyle = decorationStyle;
        this.decoratorTypeEnum = decoratorTypeEnum;

        this.position = position;

        this.resourceType = resourceType;

        this.cacheContent = cacheContent;
        this.cacheData = cacheData;
    }

    public boolean isCacheData() {
        return cacheData;
    }

    public boolean isCacheContent() {
        return cacheContent;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public String getPosition() {
        return position;
    }

    public DecorationStyle getDecorationStyle() {
        return decorationStyle;
    }

    public CommonEnum.DecoratorTypeEnum getDecoratorTypeEnum() {
        return decoratorTypeEnum;
    }
}