package com.ronrong.thymeleaf.mat.decoration;

import com.ronrong.thymeleaf.mat.IDecoration;
import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.cache.ICache;
import com.ronrong.thymeleaf.mat.cache.ICacheManager;
import com.ronrong.thymeleaf.mat.cache.StyleModuleCacheKey;
import com.ronrong.thymeleaf.mat.context.IDecorationContext;
import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;
import com.ronrong.thymeleaf.mat.entity.Module;
import com.ronrong.thymeleaf.mat.entity.Shop;
import com.ronrong.thymeleaf.mat.entity.Template;
import com.ronrong.thymeleaf.mat.util.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:装修经理
 * @author:rongshaolin
 */
// 一个经理只负责一家店铺；
    // 一个经理管理多个装修工人
    // 一个经理管理不同风格的同工种装修工人

public class DecorationManager implements IDecoration {

    private static final Logger logger = LoggerFactory.getLogger(DecorationManager.class);

    /**
     * 装修店铺以及模块
     */
    private final ICache<StyleModuleCacheKey, IDecorator> styleModuleCache; // might be null! (= no cache)


    /**
     * 装修配置
     */
    private final IDecorationConfiguration decorationConfiguration;

    public DecorationManager(IDecorationConfiguration decorationConfiguration) {

        super();

        Validate.notNull(decorationConfiguration, "decorationConfiguration cannot be null");


        this.decorationConfiguration = decorationConfiguration;

        final ICacheManager cacheManager = this.decorationConfiguration.getCacheManager();

        if (cacheManager == null) {
            this.styleModuleCache = null;
        } else {
            this.styleModuleCache = cacheManager.getShopModuleCache();
        }
    }


    /**
     * 装修施工
     * TODO: 装修工可以并行装修！！！！
     * @return
     */
    public String execute(IDecorationContext decorationContext) {


        // 装修配置
        IDecorationConfiguration decorationConfiguration = decorationContext.getConfiguration();

        // 获得装修风格
        DecorationStyle decorationStyle = decorationContext.getDecorationStyle();

        // 获得模块
        Module module = decorationContext.getModule();

        Shop shop = decorationContext.getShop();

        final StyleModuleCacheKey cacheKey = new StyleModuleCacheKey(decorationStyle, module);

        // 2 获得装修工
        // 如果缓存机制生效
        if (this.styleModuleCache != null) {

            final IDecorator cachedDecorator = this.styleModuleCache.get(cacheKey);

            if (cachedDecorator != null) {

                logger.debug("Decorator使用缓存:{}", cachedDecorator);
               return cachedDecorator.execute(decorationContext);
            }

        }

        // 3 创建装修工人
        // 3.1 获得装修公司
        AbstractDecorationCompany decorationCompany = (AbstractDecorationCompany)decorationConfiguration.getDecorationCompany();

        // 3.2 创建装修工人
        IDecorator decorator = decorationCompany.createDecorator(decorationStyle, shop, module, decorationContext);

        // 如果缓存机制生效
        if (this.styleModuleCache != null) {
            this.styleModuleCache.put(cacheKey, decorator);
        }

        // 开始装修施工
        return decorator.execute(decorationContext);

    }

    public IDecorationConfiguration getDecorationConfiguration() {
        return decorationConfiguration;
    }
}