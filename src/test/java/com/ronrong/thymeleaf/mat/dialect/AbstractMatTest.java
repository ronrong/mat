package com.ronrong.thymeleaf.mat.dialect;

import com.ronrong.thymeleaf.mat.DecorationAgent;
import com.ronrong.thymeleaf.mat.decoration.IDecorationAgent;
import com.ronrong.thymeleaf.mat.resourceresolver.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMatTest {

    protected static TemplateEngine templateEngine;

    protected static void setupThymeleaf() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        //templateEngine.addDialect("mat", new MatDialect());

        Map<ResourceType, IResourceResolver> tplResourceResolvers = new HashMap<ResourceType, IResourceResolver>();
        tplResourceResolvers.put(ResourceType.FILE, new FileTplResourceResolver());
        tplResourceResolvers.put(ResourceType.CLASS, new ClassLoaderTplResourceResolver());
        //tplResourceResolvers.put(ResourceType.DB, new DbResourceResolver());

        Map<ResourceType, IResourceResolver> dataResourceResolvers = new HashMap<ResourceType, IResourceResolver>();
        dataResourceResolvers.put(ResourceType.FILE, new FileDataResourceResolver());
        dataResourceResolvers.put(ResourceType.CLASS, new ClassLoaderDataResourceResolver());
        //dataResourceResolvers.put(ResourceType.DB, new DbResourceResolver());


        //tplResourceResolvers.put(ResourceType.DB, new SmsDBResourceResolver());
        IDecorationAgent decorationAgent = DecorationAgent.initInstance(tplResourceResolvers, dataResourceResolvers);

        templateEngine.addDialect("mat", new MatDialect(decorationAgent));

        // TODO: 注意
        // ThymeleafFacade.setTemplateEngine(templateEngine);
    }
}