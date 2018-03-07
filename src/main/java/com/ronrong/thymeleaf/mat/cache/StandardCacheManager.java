package com.ronrong.thymeleaf.mat.cache;

import com.ronrong.thymeleaf.mat.decoration.AbstractDecorator;
import com.ronrong.thymeleaf.mat.decoration.DecorationManager;
import com.ronrong.thymeleaf.mat.decoration.IDecorator;
import com.ronrong.thymeleaf.mat.entity.*;
import com.ronrong.thymeleaf.mat.processor.element.AbstractMatProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardCacheManager extends AbstractCacheManager {

    /**
     * Default cache name: "SHOP_MODULE"
     */
    public static final String DEFAULT_SHOP_MODULE_CACHE_NAME = "DEFAULT_SHOP_MODULE_CACHE";


    /**
     * Default cache name: "PAGE_TPLPOSITION"
     */
    public static final String DEFAULT_TEMPLATE_CONTENT_CACHE_NAME = "DEFAULT_TEMPLATE_CONTENT_CACHE";
    public static final String DEFAULT_TEMPLATE_DATA_CACHE_NAME = "DEFAULT_TEMPLATE_DATA_CACHE";

    public static final String DEFAULT_SHOP_CACHE_NAME = "DEFAULT_SHOP_CACHE";
    public static final String DEFAULT_PAGE_CACHE_NAME = "DEFAULT_PAGE_CACHE";
    public static final String DEFAULT_MODULE_CACHE_NAME = "DEFAULT_MODULE_CACHE";

    public static final String DEFAULT_SHOP_MODULE_CACHE_LOGGER_NAME = "DEFAULT_SHOP_MODULE_CACHE_LOGGER";
    public static final String DEFAULT_TEMPLATE_CONTENT_CACHE_LOGGER_NAME = "DEFAULT_TEMPLATE_CONTENT_CACHE_LOGGER";
    public static final String DEFAULT_TEMPLATE_DATA_CACHE_LOGGER_NAME = "DEFAULT_TEMPLATE_DATA_CACHE_LOGGER";

    public static final String DEFAULT_SHOP_CACHE_LOGGER_NAME = "DEFAULT_SHOP_CACHE_LOGGER";
    public static final String DEFAULT_PAGE_CACHE_LOGGER_NAME = "DEFAULT_PAGE_CACHE_LOGGER";
    public static final String DEFAULT_MODULE_CACHE_LOGGER_NAME = "DEFAULT_MODULE_CACHE_LOGGER";



    private String shopModuleCacheName = DEFAULT_SHOP_MODULE_CACHE_NAME;
    private String shopModuleCacheLoggerName = DEFAULT_SHOP_MODULE_CACHE_LOGGER_NAME;

    private String templateContentCache = DEFAULT_TEMPLATE_CONTENT_CACHE_NAME;
    private String templateContentCacheLoggerName = DEFAULT_TEMPLATE_CONTENT_CACHE_LOGGER_NAME;

    private String templateDataCache = DEFAULT_TEMPLATE_DATA_CACHE_NAME;
    private String templateDataCacheLoggerName = DEFAULT_TEMPLATE_DATA_CACHE_LOGGER_NAME;

    private String shopCacheName = DEFAULT_SHOP_CACHE_NAME;
    private String shopCacheLoggerName = DEFAULT_SHOP_CACHE_LOGGER_NAME;

    private String pageCacheName = DEFAULT_PAGE_CACHE_NAME;
    private String pageCacheLoggerName = DEFAULT_PAGE_CACHE_LOGGER_NAME;

    private String moduleCacheName = DEFAULT_MODULE_CACHE_NAME;
    private String moduleCacheLoggerName = DEFAULT_MODULE_CACHE_LOGGER_NAME;


    public StandardCacheManager() {
        super();
    }

    @Override
    protected ICache<StyleModuleCacheKey, IDecorator> initializeShopModuleCacheKey() {
        return new StandardCache<StyleModuleCacheKey, IDecorator>(getShopModuleCacheName(), getShopModuleCacheLogger());
    }

    @Override
    protected ICache<PageTplPositionCacheKey, TemplateContent> initializeTemplateContentCache() {

        return new StandardCache<PageTplPositionCacheKey, TemplateContent>(getTemplateContentCacheName(), getTemplateContentCacheLogger());
    }

    @Override
    protected  ICache<PageTplPositionCacheKey,TemplateData> initializeTemplateDataCache(){

        return new StandardCache<PageTplPositionCacheKey, TemplateData>(getTemplateDataCacheName(), getTemplateDataCacheLogger());
    }

    @Override
    protected ICache<String, Shop> initializeShopCache() {
        return new StandardCache<String, Shop>(getShopCacheName(), getShopCacheLogger());
    }

    @Override
    protected ICache<String, Page> initializePageCache() {
        return new StandardCache<String, Page>(getPageCacheName(), getPageCacheLogger());
    }

    @Override
    protected ICache<String, Module> initializeModuleCache() {
        return new StandardCache<String, Module>(getModuleCacheName(), getModuleCacheLogger());
    }


    public String getShopModuleCacheName() {
        return this.shopModuleCacheName;
    }

    public String getTemplateContentCacheName() {
        return this.templateContentCache;
    }
    public String getTemplateDataCacheName() {
        return this.templateDataCache;
    }

    public String getShopCacheName() {
        return shopCacheName;
    }

    public String getPageCacheName() {
        return pageCacheName;
    }

    public String getModuleCacheName() {
        return moduleCacheName;
    }

    public String getShopModuleCacheLoggerName() {
        return this.shopModuleCacheLoggerName;
    }

    public String getTemplateContentCacheLoggerName() {
        return this.templateContentCacheLoggerName;
    }


    public String getTemplateDataCacheLoggerName() {
        return this.templateDataCacheLoggerName;
    }

    public String getShopCacheLoggerName() {
        return shopCacheLoggerName;
    }

    public String getPageCacheLoggerName() {
        return pageCacheLoggerName;
    }

    public String getModuleCacheLoggerName() {
        return moduleCacheLoggerName;
    }

    public final Logger getShopCacheLogger() {
        final String loggerName = getShopCacheLoggerName();
        if (loggerName != null) {
            return LoggerFactory.getLogger(loggerName);
        }
        return LoggerFactory.getLogger(AbstractMatProcessor.class.getName() + ".cache." + getShopCacheName());
    }

    public final Logger getPageCacheLogger() {
        final String loggerName = getPageCacheLoggerName();
        if (loggerName != null) {
            return LoggerFactory.getLogger(loggerName);
        }
        return LoggerFactory.getLogger(AbstractMatProcessor.class.getName() + ".cache." + getPageCacheName());
    }


    public final Logger getModuleCacheLogger() {
        final String loggerName = getModuleCacheLoggerName();
        if (loggerName != null) {
            return LoggerFactory.getLogger(loggerName);
        }
        return LoggerFactory.getLogger(DecorationManager.class.getName() + ".cache." + getModuleCacheName());
    }




    public final Logger getShopModuleCacheLogger() {
        final String loggerName = getShopModuleCacheLoggerName();
        if (loggerName != null) {
            return LoggerFactory.getLogger(loggerName);
        }
        return LoggerFactory.getLogger(DecorationManager.class.getName() + ".cache." + getShopModuleCacheName());
    }


    public final Logger getTemplateContentCacheLogger() {
        final String loggerName = getTemplateContentCacheLoggerName();
        if (loggerName != null) {
            return LoggerFactory.getLogger(loggerName);
        }
        return LoggerFactory.getLogger(AbstractDecorator.class.getName() + ".cache." + getTemplateContentCacheName());
    }

    public final Logger getTemplateDataCacheLogger() {
        final String loggerName = getTemplateDataCacheLoggerName();
        if (loggerName != null) {
            return LoggerFactory.getLogger(loggerName);
        }
        return LoggerFactory.getLogger(AbstractDecorator.class.getName() + ".cache." + getTemplateDataCacheName());
    }

}
