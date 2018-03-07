package com.ronrong.thymeleaf.mat.decoration.standard;

import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.context.DecorationContext;
import com.ronrong.thymeleaf.mat.context.IDecorationContext;
import com.ronrong.thymeleaf.mat.decoration.AbstractDecorator;
import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;
import com.ronrong.thymeleaf.mat.entity.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:标题装修工
 * @author:rongshaolin
 */
public class StandardTitleDecorator extends AbstractDecorator {

    private static final Logger logger = LoggerFactory.getLogger(StandardSlideDecorator.class);

    public StandardTitleDecorator(IDecorationConfiguration decorationConfiguration, Shop shop, DecorationStyle decorationStyle) {
        super(decorationConfiguration, shop, decorationStyle);
    }

    /**
     * 装修施工
     *
     * @param decorationContext
     * @return
     */
    @Override
    public String execute(IDecorationContext decorationContext) {

        ((DecorationContext) decorationContext).setRenderKey("titleName");
        String result = super.execute(decorationContext);
        return result;
    }


}
