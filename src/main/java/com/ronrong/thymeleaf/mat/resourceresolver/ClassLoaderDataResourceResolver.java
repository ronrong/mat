package com.ronrong.thymeleaf.mat.resourceresolver;

import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;
import com.ronrong.thymeleaf.mat.entity.CommonEnum;
import com.ronrong.thymeleaf.mat.entity.Shop;
import com.ronrong.thymeleaf.mat.entity.Template;
import com.ronrong.thymeleaf.mat.exception.MatExecuteException;
import com.ronrong.thymeleaf.mat.templateresource.ClassLoaderTemplateResource;
import com.ronrong.thymeleaf.mat.templateresource.IDecorationResource;
import com.ronrong.thymeleaf.mat.util.ClassLoaderUtils;
import com.ronrong.thymeleaf.mat.util.StringUtils;
import com.ronrong.thymeleaf.mat.util.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

/**
 * @author:rongshaolin
 */
public class ClassLoaderDataResourceResolver extends AbstractResourceResolver {

    private final ClassLoader classLoader;
    private static final Logger logger = LoggerFactory.getLogger(ClassLoaderDataResourceResolver.class);

    public ClassLoaderDataResourceResolver() {
        this(ClassLoaderUtils.getClassLoader(ClassLoaderTplResourceResolver.class));
    }


    public ClassLoaderDataResourceResolver(final ClassLoader classLoader) {
        super();
        Validate.notNull(classLoader, "Class Loader cannot be null");
        this.classLoader = classLoader;
    }

    @Override
    public IDecorationResource resolve(IDecorationConfiguration configuration, Template template, Shop shop) {

        String resourceName = computeResourceName(configuration, template);

        String moduleFlag = template.getDecoratorTypeEnum().getModuleFlag();
        StringBuilder decoratorClassBuilder = new StringBuilder(ClassLoaderTemplateResource.class.getPackage().getName());
        decoratorClassBuilder.append(".classloader.");
        decoratorClassBuilder.append(StringUtils.captureName(moduleFlag));
        decoratorClassBuilder.append("ClassLoaderTemplateResource");

        Class  clazz = null;
        try {
            clazz  = Class.forName(decoratorClassBuilder.toString());
            Constructor clazzConstructor = clazz.getConstructor(String.class, ClassLoader.class);
            return (IDecorationResource)clazzConstructor.newInstance(resourceName, this.classLoader);

        } catch (Exception e) {
            logger.error(String.format("[createDecorator] Exception when create decorator \"%s\": %s", new Object[]{decoratorClassBuilder.toString(),e.getMessage()}), e);
            throw new MatExecuteException("Exception when create decorator", e);

        }
    }


    @Override
    protected String computeResourceName(IDecorationConfiguration configuration, Template template) {
        DecorationStyle decorationStyle = template.getDecorationStyle();
        CommonEnum.DecoratorTypeEnum decoratorTypeEnum = template.getDecoratorTypeEnum();

        return "style/" + decorationStyle + "/" + decoratorTypeEnum.getModuleFlag()+".properties";
    }
}