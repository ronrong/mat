package com.ronrong.thymeleaf.mat.decoration;

import com.ronrong.thymeleaf.mat.context.IDecorationContext;
import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;
public interface IDecoration {


    /**
     * 装修施工
     * @return
     */
    public String execute(IDecorationContext decorationContext);

}
