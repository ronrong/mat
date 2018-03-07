package com.ronrong.thymeleaf.mat;


import com.ronrong.thymeleaf.mat.cache.ICacheManager;
import com.ronrong.thymeleaf.mat.context.IDecorationContextFactory;
import com.ronrong.thymeleaf.mat.decoration.DecorationManager;
import com.ronrong.thymeleaf.mat.decoration.IDecorationAgent;
import com.ronrong.thymeleaf.mat.decoration.IDecorationCompany;
import com.ronrong.thymeleaf.mat.decoration.IDecorator;
import com.ronrong.thymeleaf.mat.resourceresolver.IResourceResolver;
import com.ronrong.thymeleaf.mat.resourceresolver.ResourceType;

import java.util.Map;
import java.util.Set;

public interface IDecorationConfiguration {


    /**
     * 预算
     */



    /**
     * 风格
     */



    /**
     * 获得当前使用的所有装修工
     * @return
     */
    public Set<IDecorator> getIDecorators();


    /**
     * 装修经理（管理）
     * @return
     */
    public DecorationManager getDecorationManager();

    /**
     * 装修中介
     * @return
     */
    public IDecorationAgent getDecorationAgent();


    /**
     * 装修公司
     * @return
     */
    public IDecorationCompany getDecorationCompany();

//    public Set<IPreDecoration> getPreDecoration(final TemplateMode templateMode);
//    public Set<IPostDecoration> getPostDecoration(final TemplateMode templateMode);
//////////////////////////////////////////////////////////////////////////


    /**
     * 缓存管理
     * @return
     */
    public ICacheManager getCacheManager();


    // 装修上下文
    public IDecorationContextFactory getDecorationContextFactory();


    public Map<ResourceType, IResourceResolver> getTplResourceResolvers();

    public Map<ResourceType, IResourceResolver> getDataResourceResolvers();
}
