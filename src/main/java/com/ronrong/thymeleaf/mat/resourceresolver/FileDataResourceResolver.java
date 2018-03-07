package com.ronrong.thymeleaf.mat.resourceresolver;

import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.entity.Shop;
import com.ronrong.thymeleaf.mat.entity.Template;
import com.ronrong.thymeleaf.mat.templateresource.FileTemplateResource;
import com.ronrong.thymeleaf.mat.templateresource.IDecorationResource;

/**
 * @author:rongshaolin
 */
public class FileDataResourceResolver  extends AbstractResourceResolver {


    @Override
    public IDecorationResource resolve(IDecorationConfiguration configuration, Template template, Shop shop) {
        String resourceName = computeResourceName(configuration, template);
        return new FileTemplateResource(resourceName, "UTF-8");
    }
}