package com.ronrong.thymeleaf.mat.decoration;

import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.ThymeleafFacade;
import com.ronrong.thymeleaf.mat.cache.ICache;
import com.ronrong.thymeleaf.mat.cache.PageTplPositionCacheKey;
import com.ronrong.thymeleaf.mat.context.DecorationContext;
import com.ronrong.thymeleaf.mat.context.IDecorationContext;
import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;
import com.ronrong.thymeleaf.mat.entity.*;
import com.ronrong.thymeleaf.mat.exception.MatExecuteException;
import com.ronrong.thymeleaf.mat.exception.MatResourceException;
import com.ronrong.thymeleaf.mat.resourceresolver.IResourceResolver;
import com.ronrong.thymeleaf.mat.templateresource.IDecorationResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.Context;

import java.io.IOException;

/**
 * 同一个模块同一种风格 使用同一个装修工
 * @author:rongshaolin
 */
public abstract class AbstractDecorator implements IDecorator {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 装修工知道装修的店铺
     */
    private final Shop shop;


    /**
     * 装修工知道装修配置
     */
    private IDecorationConfiguration decorationConfiguration;

    /**
     * 装修工知道装修风格
     */
    private DecorationStyle decorationStyle;


    protected ICache<PageTplPositionCacheKey, TemplateContent> pageTemplateContentCache; // might be null! (= no cache)

    protected ICache<PageTplPositionCacheKey, TemplateData> pageTemplateDataCache; // might be null! (= no cache)


    /**
     * 默认缓存内容，不缓存数据
     * @param decorationConfiguration
     * @param decorationStyle
     */
    public AbstractDecorator(IDecorationConfiguration decorationConfiguration, Shop shop, DecorationStyle decorationStyle) {
        this(decorationConfiguration, shop , decorationStyle, true, true);
    }

    /**
     *
     * @param decorationConfiguration
     * @param decorationStyle
     * @param cacheContent 总开关-缓存模板内容 true cached else false
     * @param cacheData 总开关-缓存模板数据 true cache ,else false
     */
    public AbstractDecorator(IDecorationConfiguration decorationConfiguration,
                             Shop shop,
                             DecorationStyle decorationStyle,
                             boolean cacheContent,
                             boolean cacheData) {

        this.decorationConfiguration = decorationConfiguration;

        this.shop = shop;
        this.decorationStyle = decorationStyle;
        if(cacheContent)   this.pageTemplateContentCache = decorationConfiguration.getCacheManager().getTemplateContentCache(true);
        if(cacheData)      this.pageTemplateDataCache = decorationConfiguration.getCacheManager().getTemplateDataCache(true);
    }


    /**
     * 获得模块模板
     *
     * @return
     */
    public void initTemplateContent(IDecorationContext decorationContext) {

        DecorationContext decorationContextInside  = (DecorationContext)decorationContext;
        Page page = decorationContextInside.getPage();
        Template template = decorationContextInside.getTemplate();
        Shop shop = decorationContextInside.getShop();
        String position = template.getPosition();

        IDecorationConfiguration decorationConfiguration = decorationContextInside.getConfiguration();


        IResourceResolver resourceResolver = decorationConfiguration.getTplResourceResolvers().get(template.getResourceType());

        if(resourceResolver == null){
            logger.error("资源解析器为空");
            throw new MatResourceException("资源解析器为空 resource type: "+ template.getResourceType());
        }

        final PageTplPositionCacheKey cacheKey = new PageTplPositionCacheKey(page, position);

        if (this.pageTemplateContentCache != null) {
            TemplateContent cached = this.pageTemplateContentCache.get(cacheKey);

            if (cached != null) {
                ((DecorationContext)decorationContext).setTemplateContent(cached);
                logger.debug("装修工：{} \n TemplateContent使用缓存：{}", this, cached);
                return ;
            }
        }
        IDecorationResource templateResource = resourceResolver.resolve(decorationConfiguration, template,shop);
        TemplateContent templateContent = new TemplateContent(templateResource, template);
        if (this.pageTemplateContentCache != null && template.isCacheContent()) {
            this.pageTemplateContentCache.put(cacheKey, templateContent);
        }


        // 获得资源，返回字符串
        try {
            templateContent.initContent();
            ((DecorationContext)decorationContext).setTemplateContent(templateContent);
            return ;
        } catch (IOException e) {
            logger.error(String.format("[get template content] Exception when get  resource \"%s\": %s", new Object[]{template.toString(),e.getMessage()}), e);
            throw new MatExecuteException("Exception when get  resource", e);
        }


    }

    /**
     * 获得渲染数据
     *
     * @return
     */
    public void initTemplateData(IDecorationContext decorationContext ) {

        DecorationContext decorationContextInside  = (DecorationContext)decorationContext;
        Page page = decorationContextInside.getPage();
        Template template = decorationContextInside.getTemplate();
        Shop shop = decorationContextInside.getShop();
        String position = template.getPosition();

        IDecorationConfiguration decorationConfiguration = decorationContextInside.getConfiguration();


        IResourceResolver resourceResolver = decorationConfiguration.getDataResourceResolvers().get(template.getResourceType());

        if(resourceResolver == null){
            logger.error("数据资源解析器为空");
            throw new MatResourceException("数据资源解析器为空 resource type: "+ template.getResourceType());
        }

        final PageTplPositionCacheKey cacheKey = new PageTplPositionCacheKey(page, position);

        if (this.pageTemplateDataCache != null) {
            TemplateData cached = this.pageTemplateDataCache.get(cacheKey);

            if (cached != null) {
                ((DecorationContext)decorationContext).setTemplateData(cached);
                logger.debug("装修工：{} \n TemplateData使用缓存：{}", this, cached);
                return ;
            }
        }

        IDecorationResource templateResource = resourceResolver.resolve(decorationConfiguration, template,shop);
        TemplateData templatedata = new TemplateData(templateResource, template);

        if (this.pageTemplateDataCache != null && template.isCacheData()) {
            this.pageTemplateDataCache.put(cacheKey, templatedata);
        }


        // 获得资源，返回字符串
        try {
            templatedata.initContent();
            ((DecorationContext)decorationContext).setTemplateData(templatedata);
            return ;
        } catch (IOException e) {
            logger.error(String.format("[get template data] Exception when get resource \"%s\": %s", new Object[]{template.toString(),e.getMessage()}), e);
            throw new MatExecuteException("Exception when get resource", e);
        }


    }

    /**
     * 获得渲染结果
     *
     * @return
     */
    public void render(IDecorationContext decorationContext ) {

        //渲染页面 =  模板 + 数据

        // 获得模板
        this.initTemplateContent(decorationContext);

        // 获得数据
        this.initTemplateData(decorationContext);


    }


    @Override
    public String execute(IDecorationContext decorationContext) {

        this.render(decorationContext);
        // 同一个装修工，装修过同一个模块
        Context context = new Context();
        context.setVariable(decorationContext.getRenderKey(), decorationContext.getTemplateData().getContent());

        String result = ThymeleafFacade.processThymeleafFile(decorationContext.getTemplateContent().getContent(), context);
        return result;
    }

    /**
     * 装修工知道装修风格
     * @return
     */
    public DecorationStyle getDecorationStyle() {

        return this.decorationStyle;
    }

    public ICache<PageTplPositionCacheKey, TemplateContent> getPageTemplateContentCache() {
        return pageTemplateContentCache;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append(", shop=").append(shop);
        sb.append(", decorationStyle=").append(decorationStyle);
        sb.append(", pageTemplateContentCache=").append(pageTemplateContentCache);
        sb.append(", pageTemplateDataCache=").append(pageTemplateDataCache);
        sb.append('}');
        return sb.toString();
    }
}