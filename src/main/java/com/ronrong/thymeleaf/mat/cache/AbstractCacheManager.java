package com.ronrong.thymeleaf.mat.cache;


import com.ronrong.thymeleaf.mat.decoration.IDecorator;
import com.ronrong.thymeleaf.mat.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractCacheManager implements ICacheManager {


    private final  Map<String, ICache> allCaches = new ConcurrentHashMap<String, ICache>();

    /**
     * 店铺与模块 == 装修工
     */
    private volatile ICache<StyleModuleCacheKey,IDecorator> shopModuleCache;
    private volatile boolean shopModuleCacheInitialized = false;

    /**
     * 页面模板 == 模板
     */
    private volatile ICache<PageTplPositionCacheKey,TemplateContent> templateContentCache;
    private volatile boolean templateContentCacheInitialized = false;


    private volatile ICache<PageTplPositionCacheKey,TemplateData> templateDataCache;
    private volatile boolean templateDataCacheInitialized = false;



    /**
     * 店铺名称 == 店铺
     */
    private volatile ICache<String,Shop> shopCache;
    private volatile boolean shopCacheInitialized = false;

    /**
     * 页面名称 == 页面
     */
    private volatile ICache<String,Page> pageCache;
    private volatile boolean pageCacheInitialized = false;


    /**
     * 模块名称 == 模块
     */
    private volatile ICache<String, Module> moduleCache;
    private volatile boolean moduleCacheInitialized = false;


    protected AbstractCacheManager() {
        super();
    }


    public final ICache<StyleModuleCacheKey, IDecorator> getShopModuleCache() {
        if (!this.shopModuleCacheInitialized) {
            synchronized(this) {
                if (!this.shopModuleCacheInitialized) {
                    this.shopModuleCache = initializeShopModuleCacheKey();
                    this.addCache(this.shopModuleCache);
                    this.shopModuleCacheInitialized = true;
                }
            }
        }
        return this.shopModuleCache;
    }

    public final ICache<PageTplPositionCacheKey, TemplateContent> getTemplateContentCache() {
        if (!this.templateContentCacheInitialized) {
            synchronized(this) {
                if (!this.templateContentCacheInitialized) {
                    this.templateContentCache = initializeTemplateContentCache();
                    this.addCache(this.templateContentCache);
                    this.templateContentCacheInitialized = true;
                }
            }
        }
        return this.templateContentCache;
    }

    public final ICache<PageTplPositionCacheKey, TemplateContent> getTemplateContentCache(boolean isNew) {

        if (isNew) {

         return this.addCache(initializeTemplateContentCache());
        }
        else {
            return getTemplateContentCache();
        }
    }


    public final ICache<PageTplPositionCacheKey, TemplateData> getTemplateDataCache() {
        if (!this.templateDataCacheInitialized) {
            synchronized(this) {
                if (!this.templateDataCacheInitialized) {
                    this.templateDataCache = initializeTemplateDataCache();
                    this.addCache(this.templateDataCache);
                    this.templateDataCacheInitialized = true;
                }
            }
        }
        return this.templateDataCache;
    }

    public final ICache<PageTplPositionCacheKey, TemplateData> getTemplateDataCache(boolean isNew) {

        if (isNew) {
            return this.addCache(initializeTemplateDataCache());
        }
        else {
            return getTemplateDataCache();
        }
    }

    @Override
    public ICache<String, Module> getModuleCache() {
        if (!this.moduleCacheInitialized) {
            synchronized(this) {
                if (!this.moduleCacheInitialized) {
                    this.moduleCache = initializeModuleCache();
                    this.addCache(this.moduleCache);
                    this.moduleCacheInitialized = true;
                }
            }
        }
        return this.moduleCache;
    }

    @Override
    public ICache<String, Page> getPageCache() {
        if (!this.pageCacheInitialized) {
            synchronized(this) {
                if (!this.pageCacheInitialized) {
                    this.pageCache = initializePageCache();
                    this.addCache(this.pageCache);
                    this.pageCacheInitialized = true;
                }
            }
        }
        return this.pageCache;
    }

    @Override
    public ICache<String, Shop> getShopCache() {
        if (!this.shopCacheInitialized) {
            synchronized(this) {
                if (!this.shopCacheInitialized) {
                    this.shopCache = initializeShopCache();
                    this.addCache(this.shopCache);
                    this.shopCacheInitialized = true;
                }
            }
        }
        return this.shopCache;
    }

    public <K, V> ICache<K, V> getSpecificCache(final String name) {
        return this.allCaches.get(name);
    }

    public List<String> getAllSpecificCacheNames() {

        return new ArrayList<String>(this.allCaches.keySet());
    }


    public void clearAllCaches() {

        final List<String> allSpecificCacheNamesObj = getAllSpecificCacheNames();
        if (allSpecificCacheNamesObj != null) {
            for (final String specificCacheName : allSpecificCacheNamesObj) {
                final ICache<?,?> specificCache = getSpecificCache(specificCacheName);
                if (specificCache != null) {
                    specificCache.clear();
                }
            }
        }

    }

    private ICache addCache(ICache<?, ?> cache) {
        if (cache instanceof  StandardCache) {

            return this.allCaches.put(((StandardCache)cache).getName(), cache);
        }
        else {
            return this.allCaches.put(cache.toString(), cache);
        }


    }
    protected abstract ICache<StyleModuleCacheKey,IDecorator> initializeShopModuleCacheKey();

    protected abstract ICache<PageTplPositionCacheKey,TemplateContent> initializeTemplateContentCache();
    protected abstract ICache<PageTplPositionCacheKey,TemplateData> initializeTemplateDataCache();

    protected abstract ICache<String,Shop> initializeShopCache();
    protected abstract ICache<String,Page> initializePageCache();
    protected abstract ICache<String,Module> initializeModuleCache();



}
