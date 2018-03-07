package com.ronrong.thymeleaf.mat.entity;

import com.ronrong.thymeleaf.mat.templateresource.IDecorationResource;

import java.io.IOException;

/**
 * @author:rongshaolin
 */
public class TemplateContent implements ITemplate {

    /**
     * 仅限内部使用
     */
    private String content;
    private Template template;
    private final IDecorationResource templateResource;


    public TemplateContent(IDecorationResource templateResource, Template template) {
        this.templateResource = templateResource;
        this.template = template;
    }


    public String getContent() {
        return content;
    }

    public Template getTemplate() {
        return template;
    }

    public IDecorationResource getTemplateResource() {
        return templateResource;
    }

    public void initContent() throws IOException {
        this.content = (String)this.templateResource.content();
    }
}