package com.ronrong.thymeleaf.mat;

import com.ronrong.thymeleaf.mat.cache.ICacheManager;
import com.ronrong.thymeleaf.mat.cache.StandardCacheManager;
import com.ronrong.thymeleaf.mat.context.IDecorationContextFactory;
import com.ronrong.thymeleaf.mat.context.StandardDecorationContextFactory;
import com.ronrong.thymeleaf.mat.decoration.AbstractDecorationAgent;
import com.ronrong.thymeleaf.mat.decoration.IDecorationCompany;
import com.ronrong.thymeleaf.mat.decoration.standard.StandardDecorationCompany;
import com.ronrong.thymeleaf.mat.exception.MatDecorationException;
import com.ronrong.thymeleaf.mat.exception.MatResourceException;
import com.ronrong.thymeleaf.mat.resourceresolver.IResourceResolver;
import com.ronrong.thymeleaf.mat.resourceresolver.ResourceType;
import com.ronrong.thymeleaf.mat.util.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;

/**
 * @author:rongshaolin
 */
public class DecorationAgent extends AbstractDecorationAgent implements Serializable {

    private volatile boolean initialized = false;
    private static volatile DecorationAgent decorationAgent;
    /**
     * 装修上下文工厂
     */
    private IDecorationContextFactory decorationContextFactory;

    private IDecorationConfiguration decorationConfiguration;

    /**
     * 装修公司
     */
    private IDecorationCompany decorationCompany;

    private Map<ResourceType, IResourceResolver> tplResourceResolvers;

    private Map<ResourceType, IResourceResolver> dataResourceResolvers;

    /**
     * 缓存管理
     */
    private ICacheManager cacheManager;

    private static final Logger logger = LoggerFactory.getLogger(DecorationAgent.class);



    DecorationAgent(Map<ResourceType, IResourceResolver> tplResourceResolvers, Map<ResourceType, IResourceResolver> dataResourceResolvers) {

        super();
        if (decorationAgent != null) {
            throw new RuntimeException("不能使用构造函数，再次初始化一个实例");
        }
        // 初始化缓存管理器
        setCacheManager(new StandardCacheManager());
        // 初始化装修上下文工厂
        setDecorationContextFactory(new StandardDecorationContextFactory());
        // 初始化装修公司
        setDecorationCompany(new StandardDecorationCompany(this));

        if (tplResourceResolvers == null || tplResourceResolvers.size() == 0) {
           throw new MatResourceException("资源解析配置为空");
        }

        this.tplResourceResolvers = tplResourceResolvers;
        this.dataResourceResolvers = dataResourceResolvers;

        logger.info("资源解析配置：tplResourceResolvers：" + tplResourceResolvers);
        logger.info("资源解析配置：dataResourceResolvers：" + dataResourceResolvers);


    }

    public  DecorationAgent(Object tplResourceResolvers,Object dataResourceResolvers,String flag) {
        Map<ResourceType, IResourceResolver> tplResourceResolversMap = (Map<ResourceType, IResourceResolver>) tplResourceResolvers;
        Map<ResourceType, IResourceResolver> dataResourceResolversMap = (Map<ResourceType, IResourceResolver>) dataResourceResolvers;
        initInstance(tplResourceResolversMap,dataResourceResolversMap);
    }

    public static DecorationAgent initInstance(Map<ResourceType, IResourceResolver> tplResourceResolvers,
                                               Map<ResourceType, IResourceResolver> dataResourceResolvers) {

        if (decorationAgent == null) {
            synchronized (DecorationAgent.class) {
                if (decorationAgent == null) {
                    decorationAgent = new DecorationAgent(tplResourceResolvers, dataResourceResolvers);
                    decorationAgent.initialize();
                }
            }
        }

        return decorationAgent;
    }

    public static DecorationAgent getInstance() {
        if (decorationAgent == null) {
            throw new MatDecorationException("先初始化DecorationAgent 调用initInstance方法");
        }
        return decorationAgent;
    }


    /**
     * 初始化装修中介
     */
    final void initialize() {

        if (!this.initialized) {

            synchronized (this) {

                if (!this.initialized) {

                    logger.debug("[DecorationAgent] 初始化开始");

                    // 找到装修公司 this.decorationCompany

                    // 中介起草配置
                    this.decorationConfiguration = new DecorationConfiguration(this.decorationCompany, this.tplResourceResolvers, this.dataResourceResolvers, this.cacheManager, this.decorationContextFactory);

                    ((DecorationConfiguration)this.decorationConfiguration).takeEffect();


                    this.initialized = true;

                    logger.debug("[DecorationAgent] 初始化完成");

                }

            }

        }

    }
    /**
     * <p>
     * 创建装修公司
     * </p>
     * 一个装修公司的配置是统一的
     *
     */
    public IDecorationCompany getDecorationCompany() {
        return this.decorationConfiguration.getDecorationCompany();
    }

    public IDecorationConfiguration getConfiguration() {
        return this.decorationConfiguration;
    }

    public void setDecorationCompany(IDecorationCompany decorationCompany) {
        this.decorationCompany = decorationCompany;
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
     * 设置缓存管理器，如果没有设置将不使用缓存
     * @param cacheManager
     */
    public void setCacheManager(final ICacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    public void setDecorationContextFactory(StandardDecorationContextFactory decorationContextFactory) {
        this.decorationContextFactory = decorationContextFactory;
    }

    public void putTemplateResolver(ResourceType resourceType, IResourceResolver resourceResolver) {
        Validate.notNull(resourceResolver, "resource Resolver cannot be null");
        this.tplResourceResolvers.put(resourceType, resourceResolver);
    }
}