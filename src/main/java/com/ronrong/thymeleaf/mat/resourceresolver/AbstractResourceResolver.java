package com.ronrong.thymeleaf.mat.resourceresolver;

import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.entity.Template;

/**
 * @author:rongshaolin
 */

public abstract class AbstractResourceResolver implements  IResourceResolver{

    private String name = this.getClass().getName();
    private Integer order = null;


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getOrder() {
        return this.order;
    }


    protected String computeResourceName( IDecorationConfiguration configuration, Template template) {

       return "";

    }
}