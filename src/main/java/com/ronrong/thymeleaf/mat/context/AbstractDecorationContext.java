package com.ronrong.thymeleaf.mat.context;

import com.ronrong.thymeleaf.mat.util.Validate;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  大多数{@link IDecorationContext}实现的抽象基类。
 * </p>
 *
 */
public abstract class AbstractDecorationContext implements IDecorationContext {


    private final Map<String,Object> variables;
    private Locale locale;

    protected AbstractDecorationContext() {
        this(Locale.getDefault());
    }


    protected AbstractDecorationContext(final Locale locale) {
        super();
        Validate.notNull(locale, "locale不能为null");
        this.locale = locale;
        this.variables = new LinkedHashMap<String, Object>(10);
    }


    protected AbstractDecorationContext(final Locale locale, final Map<String, Object> variables) {
        super();
        Validate.notNull(locale, "locale不能为null");
        Validate.notNull(variables, "Variables不能为null");
        this.locale = locale;
        this.variables = new LinkedHashMap<String, Object>(variables);
    }


    public final Locale getLocale() {
        return this.locale;
    }

    public final boolean containsVariable(final String name) {
        return this.variables.containsKey(name);
    }

    public final Set<String> getVariableNames() {
        return this.variables.keySet();
    }


    public final Object getVariable(final String name) {
        return this.variables.get(name);
    }


    /**
     * <p>
     *   设置要使用的语言环境。
     * </p>
     *
     * #param locale 语言环境。
     */
    public final void setLocale(final Locale locale) {
        Validate.notNull(locale, "Locale cannot be null");
        this.locale = locale;
    }


    /**
     * <p>
     *   在上下文中设置一个新变量。
     * </p>
     *
     * @param name 变量的名称。
     * @param value 变量的值。
     */
    public final void setVariable(final String name, final Object value) {
        this.variables.put(name, value);
    }


    /**
     * <p>
     *   一次将多个变量设置到上下文中。
     * </p>
     *
     * @param variables 要设置的变量。
     */
    public void setVariables(final Map<String, Object> variables) {
        if (variables == null) {
            return;
        }
        this.variables.putAll(variables);
    }


    /**
     * <p>
     *   从上下文中删除一个变量。
     * </p>
     *
     * #param name 要删除的变量的名称。
     */
    public final void removeVariable(final String name) {
        this.variables.remove(name);
    }


    /**
     * <p>
     *   从上下文中删除所有变量。
     * </p>
     */
    public final void clearVariables() {
        this.variables.clear();
    }


}
