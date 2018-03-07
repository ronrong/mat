
package com.ronrong.thymeleaf.mat.cache;

import java.util.List;

import com.ronrong.thymeleaf.mat.decoration.IDecorator;
import com.ronrong.thymeleaf.mat.entity.*;


public interface ICacheManager {



    public <K,V> ICache<K,V> getSpecificCache(final String name);


    public List<String> getAllSpecificCacheNames();

    

    public void clearAllCaches();


    /**
     * 返回已经分配的装修工的缓存
     * @return
     */
    ICache<StyleModuleCacheKey,IDecorator> getShopModuleCache();

    ICache<PageTplPositionCacheKey, TemplateContent> getTemplateContentCache();
    ICache<PageTplPositionCacheKey, TemplateContent> getTemplateContentCache(boolean isNew);
    ICache<PageTplPositionCacheKey, TemplateData> getTemplateDataCache();
    ICache<PageTplPositionCacheKey, TemplateData> getTemplateDataCache(boolean isNew);



    ICache<String,Shop> getShopCache();

    ICache<String,Page> getPageCache();

    ICache<String,Module> getModuleCache();
}
