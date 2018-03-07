package com.ronrong.thymeleaf.mat.context;

import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;
import com.ronrong.thymeleaf.mat.entity.*;

import java.util.Locale;
import java.util.Map;

/**
 *
 */
public final class DecorationContext extends AbstractDecorationContext {

    private IDecorationConfiguration decorationConfiguration;

    private Template template;

    private Module module;

    private DecorationStyle decorationStyle;

    private Page page;
    private Shop shop;
    private TemplateData templateData;
    private TemplateContent templateContent;
    /**
     * 界面渲染的key
     */
    private String renderKey = "defaultRenderKey";


    public DecorationContext(final Locale locale) {
        super(locale);
    }

    public DecorationContext(
            final IDecorationConfiguration decorationConfiguration,
            final Shop shop,
            final Page page,
            final Module module,
            final Template template,
            final Map<String, Object> variables) {

        this.decorationConfiguration = decorationConfiguration;

        this.shop = shop;
        this.page = page;
        this.module = module;

        this.template = template;
        this.decorationStyle = this.template.getDecorationStyle();

        if (variables != null) {
            setVariables(variables);
        }

    }

    @Override
    public IDecorationConfiguration getConfiguration() {
        return this.decorationConfiguration;
    }

    /**
     * 获得模板数据和资源
     *
     * @return
     */
    @Override
    public Template getTemplate() {
        return this.template;
    }

    /**
     * 获得装修风格
     *
     * @return
     */
    @Override
    public DecorationStyle getDecorationStyle() {
        return this.decorationStyle;
    }

    /**
     * 获得模块
     *
     * @return
     */
    @Override
    public Module getModule() {
        return this.module;
    }

    /**
     * @return
     */
    @Override
    public TemplateData getTemplateData() {
        return this.templateData;
    }

    /**
     * @return
     */
    @Override
    public TemplateContent getTemplateContent() {
        return this.templateContent;
    }

    @Override
    public String getRenderKey() {
        return this.renderKey;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setVariables(final Map<String, Object> variables) {

        if (variables == null || variables.isEmpty()) {
            return;
        }

        for (final Map.Entry<String, Object> entry : variables.entrySet()) {
                this.setVariable(entry.getKey(), entry.getValue());
            }
        }

    public void setRenderKey(String renderKey) {
        this.renderKey = renderKey;
    }

    public void setTemplateData(TemplateData templateData) {
        this.templateData = templateData;
    }

    public void setTemplateContent(TemplateContent templateContent) {
        this.templateContent = templateContent;
    }
}
