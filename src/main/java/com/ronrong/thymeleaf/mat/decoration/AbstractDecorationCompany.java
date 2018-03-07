package com.ronrong.thymeleaf.mat.decoration;

import com.ronrong.thymeleaf.mat.context.IDecorationContext;
import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;
import com.ronrong.thymeleaf.mat.entity.Module;
import com.ronrong.thymeleaf.mat.entity.Shop;

/**
 * @author:rongshaolin
 */
public abstract class AbstractDecorationCompany implements IDecorationCompany{


    public abstract DecorationManager initManager();

    public abstract AbstractDecorator createDecorator(DecorationStyle decorationStyle, Shop shop, Module module, final IDecorationContext context);
}