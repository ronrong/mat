package com.ronrong.thymeleaf.mat.context;

import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;
import com.ronrong.thymeleaf.mat.entity.*;

import java.util.Locale;
import java.util.Set;

/**
 */
public interface IDecorationContext {

    /**
     * <p>
     *   返回应该用于处理模板的区域设置。
     * </p>
     *
     * @return 要使用的语言环境。
     */
    public Locale getLocale();

    /**
     * <p>
     *  检查特定变量是否已包含在此上下文中。
     * </p>
     *
     * #param 要检查的变量的名称。
     * #return 如果该变量已被包含，<tt>true</tt> 否则, <tt>false</tt> 。
     */
    public boolean containsVariable(final String name);

    /**
     * <p>
     *  获取包含在此上下文中的所有变量名称的列表。
     * </p>
     *
     * @return 变量名称。
     */
    public Set<String> getVariableNames();

    /**
     * <p>
     *   按名称检索特定变量。
     * </p>
     *
     * @param name 要检索的变量的名称。
     * @return 变量的值。
     */
    public Object getVariable(final String name);

    public IDecorationConfiguration getConfiguration();

    /**
     * 获得模板数据和资源
     * @return
     */
    public Template getTemplate();


    /**
     * 获得装修风格
     * @return
     */
    public DecorationStyle getDecorationStyle();

    /**
     * 获得模块
     * @return
     */
    public Module getModule();

    public Shop getShop();

    /**
     *
     * @return
     */
    public TemplateData getTemplateData();

    /**
     *
     * @return
     */
    public TemplateContent getTemplateContent();

    public String getRenderKey();
}
