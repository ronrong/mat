package com.ronrong.thymeleaf.mat.resourceresolver;

import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;
import com.ronrong.thymeleaf.mat.entity.CommonEnum;
import com.ronrong.thymeleaf.mat.entity.Shop;
import com.ronrong.thymeleaf.mat.entity.Template;
import com.ronrong.thymeleaf.mat.templateresource.ClassLoaderTemplateResource;
import com.ronrong.thymeleaf.mat.templateresource.IDecorationResource;
import com.ronrong.thymeleaf.mat.util.ClassLoaderUtils;
import com.ronrong.thymeleaf.mat.util.Validate;

/**
 * @author:rongshaolin
 */
public class ClassLoaderTplResourceResolver extends AbstractResourceResolver {

    private final ClassLoader classLoader;


    public ClassLoaderTplResourceResolver() {
        this(ClassLoaderUtils.getClassLoader(ClassLoaderTplResourceResolver.class));
    }


    public ClassLoaderTplResourceResolver(final ClassLoader classLoader) {
        super();
        Validate.notNull(classLoader, "Class Loader cannot be null");
        this.classLoader = classLoader;
    }

    @Override
    public IDecorationResource resolve(IDecorationConfiguration configuration, Template template, Shop shop) {

        String resourceName = computeResourceName(configuration, template);
        return new ClassLoaderTemplateResource(this.classLoader, resourceName, "UTF-8");
    }


    @Override
    protected String computeResourceName(IDecorationConfiguration configuration, Template template) {
        DecorationStyle decorationStyle = template.getDecorationStyle();
        CommonEnum.DecoratorTypeEnum decoratorTypeEnum = template.getDecoratorTypeEnum();

        return "style/" + decorationStyle + "/" + decoratorTypeEnum.getModuleFlag()+".html";
    }
}