package com.ronrong.thymeleaf.mat.context;


import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.entity.Module;
import com.ronrong.thymeleaf.mat.entity.Page;
import com.ronrong.thymeleaf.mat.entity.Shop;
import com.ronrong.thymeleaf.mat.entity.Template;
import org.thymeleaf.context.IContext;

public interface IDecorationContextFactory {


    public IDecorationContext createDecorationContext(
            final IDecorationConfiguration decorationConfiguration,
            final Shop shop,
            final Page page,
            final Module module,
            final Template template,
            final IContext context) ;


}
