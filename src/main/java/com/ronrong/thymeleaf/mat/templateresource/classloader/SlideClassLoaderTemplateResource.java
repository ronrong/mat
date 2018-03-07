package com.ronrong.thymeleaf.mat.templateresource.classloader;

import com.ronrong.thymeleaf.mat.entity.standard.StandardSlideImg;
import com.ronrong.thymeleaf.mat.templateresource.ClassLoaderTemplateResource;
import com.ronrong.thymeleaf.mat.templateresource.IDecorationResource;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * @author:rongshaolin
 */
public class SlideClassLoaderTemplateResource  implements IDecorationResource {
    private ClassLoaderTemplateResource classLoaderTemplateResource;


    public SlideClassLoaderTemplateResource(final String path, final ClassLoader classLoader) {
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
        // TODO: 简单的读取指定数量的配置，只做例子！！
        List<StandardSlideImg> slideList = new ArrayList<StandardSlideImg>();
        Properties properties = null;
        try {
            properties =  new Properties();
            properties.load(classLoaderTemplateResource.reader());

        } catch (Exception e) {
            e.printStackTrace();
        }
        int count = 0;
        while(count < properties.values().size()){
            count ++;
            if(count%3 == 0) {
                StandardSlideImg slide = new StandardSlideImg();
                slide.setImgUrl((String)properties.get("slide.imgUrl[" + (count/3-1) + "]"));
                slide.setDescription((String)properties.get("slide.description[" + (count/3-1) + "]"));
                slide.setToUrl((String)properties.get("slide.toUrl[" + (count/3-1) + "]"));
                slideList.add(slide);
            }
        }

        return slideList;
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