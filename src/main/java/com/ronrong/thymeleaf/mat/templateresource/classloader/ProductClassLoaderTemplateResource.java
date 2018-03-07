package com.ronrong.thymeleaf.mat.templateresource.classloader;

import com.ronrong.thymeleaf.mat.entity.standard.StandardProduct;
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
public class ProductClassLoaderTemplateResource implements IDecorationResource {
    private ClassLoaderTemplateResource classLoaderTemplateResource;


    public ProductClassLoaderTemplateResource(final String path, final ClassLoader classLoader) {
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
        List<StandardProduct> productList = new ArrayList<StandardProduct>();
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
                StandardProduct pdt = new StandardProduct();
                pdt.setName((String)properties.get("product.name[" + (count/3-1) + "]"));
                pdt.setImgUrl((String)properties.get("product.imgUrl[" + (count/3-1) + "]"));
                pdt.setDescription((String)properties.get("product.description[" + (count/3-1) + "]"));
                productList.add(pdt);
            }
        }

        return productList;
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