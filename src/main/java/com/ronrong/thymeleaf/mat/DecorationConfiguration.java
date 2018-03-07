package com.ronrong.thymeleaf.mat;

import com.ronrong.thymeleaf.mat.cache.ICacheManager;
import com.ronrong.thymeleaf.mat.context.IDecorationContextFactory;
import com.ronrong.thymeleaf.mat.decoration.*;
import com.ronrong.thymeleaf.mat.resourceresolver.IResourceResolver;
import com.ronrong.thymeleaf.mat.resourceresolver.ResourceType;
import com.ronrong.thymeleaf.mat.util.Validate;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 装修配置；
 *
 */
public class DecorationConfiguration implements IDecorationConfiguration {

    /**
     * 装修中介
     */
    private IDecorationAgent decorationAgent;

    /**
     * 装修公司
     */
    private IDecorationCompany decorationCompany;

    /**
     * 资源解析器（物料yunshu）
     */
    private Map<ResourceType, IResourceResolver> tplResourceResolvers;

    private Map<ResourceType, IResourceResolver> dataResourceResolvers;

    /**
     * 装修管理
     */
    private DecorationManager decorationManager;

    /**
     * 缓存管理
     */
    private final ICacheManager cacheManager;


    /**
     * 装修上下文工厂
     */
    private final IDecorationContextFactory decorationContextFactory;



    public DecorationConfiguration(IDecorationCompany decorationCompany,
                                   Map<ResourceType, IResourceResolver> tplResourceResolvers,
                                   Map<ResourceType, IResourceResolver> dataResourceResolvers,
                                   ICacheManager cacheManager,
                                   IDecorationContextFactory decorationContextFactory) {
        super();
        Validate.notNull(decorationContextFactory, "decorationContextFactory set cannot be null");

        this.tplResourceResolvers = tplResourceResolvers;
        this.dataResourceResolvers = dataResourceResolvers;
        this.cacheManager = cacheManager;
        this.decorationCompany = decorationCompany;
        this.decorationContextFactory = decorationContextFactory;
    }


    /**
     * 使配置生效
     * 分配装修经理
     */
    void takeEffect() {

        ((AbstractDecorationCompany)this.decorationCompany).initManager();

    }


    /**
     * 装修工
     *
     * @return
     */
    @Override
    public Set<IDecorator> getIDecorators() {

        Set set = new HashSet(this.cacheManager.getShopModuleCache().values());
        return set;
    }

    /**
     * 缓存管理
     *
     * @return
     */
    @Override
    public ICacheManager getCacheManager() {
        return this.cacheManager;
    }

    /**
     * 装修经理（管理）
     *
     * @return
     */
    @Override
    public DecorationManager getDecorationManager() {
        return this.decorationManager;
    }

    /**
     * 装修中介
     *
     * @return
     */
    @Override
    public IDecorationAgent getDecorationAgent() {
        return this.decorationAgent;
    }

    /**
     * 装修公司
     *
     * @return
     */
    @Override
    public IDecorationCompany getDecorationCompany() {
        return this.decorationCompany;
    }

    @Override
    public IDecorationContextFactory getDecorationContextFactory() {
        return this.decorationContextFactory;
    }

    @Override
    public Map<ResourceType, IResourceResolver> getTplResourceResolvers() {
        return this.tplResourceResolvers;
    }

    public Map<ResourceType, IResourceResolver> getDataResourceResolvers() {
        return dataResourceResolvers;
    }
}
