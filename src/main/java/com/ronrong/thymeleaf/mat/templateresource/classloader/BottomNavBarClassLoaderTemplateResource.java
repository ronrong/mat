package com.ronrong.thymeleaf.mat.templateresource.classloader;

import com.ronrong.thymeleaf.mat.templateresource.ClassLoaderTemplateResource;
import com.ronrong.thymeleaf.mat.templateresource.IDecorationResource;

import java.io.IOException;
import java.io.Reader;

/**
 * @author:rongshaolin
 */
public class BottomNavBarClassLoaderTemplateResource implements IDecorationResource {
    private ClassLoaderTemplateResource classLoaderTemplateResource;



    public BottomNavBarClassLoaderTemplateResource(final String path, final ClassLoader classLoader) {
        classLoaderTemplateResource = new ClassLoaderTemplateResource(classLoader, path, "UTF-8");
    }

    @Override
    public String getDescription() {
        return classLoaderTemplateResource.getDescription();
    }

    /**
     * <p>
     * 返回资源的基本名称。
     * </p>
     */
    @Override
    public String getBaseName() {
        return classLoaderTemplateResource.getBaseName();
    }

    /**
     * <p>
     * 确定由此对象表示的资源是否确实存在或不存在。
     * </p>
     */
    @Override
    public boolean exists() {
        return classLoaderTemplateResource.exists();
    }

    /**
     * <p>
     * 资源返回的内容。
     * </p>
     */
    @Override
    public Object content() throws IOException {
        // TODO: 查询数据库

        return null;
    }

    /**
     * 返回可用于使用模板内容的{@link Reader}。
     *
     * @throws IOException
     */
    @Override
    public Reader reader() throws IOException {
        return this.classLoaderTemplateResource.reader();
    }

    /**
     * <p>
     *
     * </p>
     *
     * @param relativeLocation
     */
    @Override
    public IDecorationResource relative(String relativeLocation) {
        return this.classLoaderTemplateResource.relative(relativeLocation);
    }
}