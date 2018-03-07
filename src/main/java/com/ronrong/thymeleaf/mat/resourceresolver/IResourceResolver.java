package com.ronrong.thymeleaf.mat.resourceresolver;

import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.entity.Shop;
import com.ronrong.thymeleaf.mat.entity.Template;
import com.ronrong.thymeleaf.mat.templateresource.IDecorationResource;

public interface IResourceResolver {

    public String getName();

    public Integer getOrder();

    public IDecorationResource resolve(final IDecorationConfiguration configuration, Template template, Shop shop);

}
