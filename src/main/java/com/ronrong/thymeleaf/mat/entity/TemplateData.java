package com.ronrong.thymeleaf.mat.entity;

import com.ronrong.thymeleaf.mat.templateresource.IDecorationResource;

import java.io.IOException;

/**
 * @author:rongshaolin
 */
public class TemplateData implements ITemplate {
    /**
     * 仅限内部使用
     */
    private Object content;
    private Template template;
    private final IDecorationResource templateResource;



    public TemplateData(IDecorationResource templateResource, Template template) {
        this.templateResource = templateResource;
        this.template = template;
    }


    public Object getContent() {
        return content;
    }

    public Template getTemplate() {
        return template;
    }

    public IDecorationResource getTemplateResource() {
        return templateResource;
    }

    public void initContent() throws IOException {
        this.content = this.templateResource.content();
    }
}