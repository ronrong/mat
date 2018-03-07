package com.ronrong.thymeleaf.mat.processor.element;


import com.ronrong.thymeleaf.mat.DecorationAgent;
import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.cache.ICache;
import com.ronrong.thymeleaf.mat.context.IDecorationContext;
import com.ronrong.thymeleaf.mat.context.IDecorationContextFactory;
import com.ronrong.thymeleaf.mat.decoration.DecorationManager;
import com.ronrong.thymeleaf.mat.decoration.IDecorationCompany;
import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;
import com.ronrong.thymeleaf.mat.entity.*;
import com.ronrong.thymeleaf.mat.resourceresolver.ResourceType;
import com.ronrong.thymeleaf.mat.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * @author:rongshaolin
 */
public abstract class AbstractMatProcessor extends AbstractElementTagProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AbstractMatProcessor(TemplateMode templateMode, String dialectPrefix, String elementName, boolean prefixElementName, String attributeName, boolean prefixAttributeName, int precedence) {
        super(templateMode, dialectPrefix, elementName, prefixElementName, attributeName, prefixAttributeName, precedence);
    }


    @Override
    protected void doProcess(ITemplateContext iTemplateContext,
                             IProcessableElementTag iProcessableElementTag,
                             IElementTagStructureHandler iElementTagStructureHandler) {

        long processBeg = System.currentTimeMillis();

        String shopName = iProcessableElementTag.getAttributeValue("shop");
        String pageName = iProcessableElementTag.getAttributeValue("page");
        String styleName = iProcessableElementTag.getAttributeValue("style");
        String resourceTypeName = iProcessableElementTag.getAttributeValue("resourcetype");
        String positionName = iProcessableElementTag.getAttributeValue("position");
        String descriptionName = iProcessableElementTag.getAttributeValue("description");
        String cachedataName = iProcessableElementTag.getAttributeValue("cachedata");
        String cachecontentName = iProcessableElementTag.getAttributeValue("cachecontent");

        boolean cachecontent = true;  // 默认缓存模板
        boolean cachedata    = false; // 默认不缓存数据

        /***
         *默认缓存模板内容
         */
        if(!StringUtils.isEmptyOrWhitespace(cachecontentName)){
            cachecontent = "true".equals(cachecontentName);
        }
        /**
         * 默认不缓存模板数据
         */
        if(!StringUtils.isEmptyOrWhitespace(cachedataName)){
            cachedata    = "true".equals(cachedataName);
        }



        getLogger().debug(descriptionName);
        // 中介开始工作
        DecorationAgent decorationAgent = DecorationAgent.getInstance();

        IDecorationCompany decorationCompany = decorationAgent.getDecorationCompany();
        DecorationStyle decorationStyle = DecorationStyle.parse(styleName);
        IDecorationConfiguration decorationConfiguration = decorationAgent.getConfiguration();
        ICache<String, Shop> shopCache = decorationConfiguration.getCacheManager().getShopCache();
        ICache<String, Page> pageCache = decorationConfiguration.getCacheManager().getPageCache();
        ICache<String, Module> moduleCache = decorationConfiguration.getCacheManager().getModuleCache();

        Shop shop = shopCache.get(shopName);
        if(shop == null){
            shop = new Shop(shopName, null);
            shopCache.put(shopName, shop);
        } else {
            logger.debug("Shop使用缓存:{}", shop);
        }
        Page page = pageCache.get(pageName);
        if(page == null){
            page = new Page(pageName, null);
            pageCache.put(pageName, page);
        } else {
            logger.debug("Page使用缓存:{}", page);
        }
        Module module = moduleCache.get(getDecoratorType().getModuleFlag());
        if(module == null){
            module = new Module(getDecoratorType().getName(), getDecoratorType(), null);
            moduleCache.put(getDecoratorType().getModuleFlag(), module);
        } else {
            logger.debug("Module使用缓存:{}", module);
        }

        ResourceType resourceType = ResourceType.parse(resourceTypeName);
        Template template = new Template(decorationStyle, resourceType, positionName, getDecoratorType(), cachecontent, cachedata);

        IDecorationContextFactory decorationContextFactory = decorationConfiguration.getDecorationContextFactory();
        IDecorationContext context = decorationContextFactory.createDecorationContext(decorationConfiguration, shop, page, module, template, iTemplateContext);

        DecorationManager decorationManager = decorationCompany.getDecorationManager();

        String htmlResult = decorationManager.execute(context);

        // 指示引擎用指定的模型替换整个元素。
        iElementTagStructureHandler.replaceWith(htmlResult, true);

        logger.debug("处理器：{} ， 使用时间：{}ms", this.getClass().getName(), System.currentTimeMillis() - processBeg);
    }


    public abstract CommonEnum.DecoratorTypeEnum getDecoratorType();

    public abstract Logger getLogger();
}