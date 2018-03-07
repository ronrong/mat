package com.ronrong.thymeleaf.mat.decoration;

import com.ronrong.thymeleaf.mat.context.IDecorationContext;
import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;

/**
 * @author:rongshaolin
 */
public interface IDecorator extends IDecoration{

    public void initTemplateContent(IDecorationContext decorationContext);
    public void initTemplateData(IDecorationContext decorationContext);
    public void render(IDecorationContext decorationContext);

}
