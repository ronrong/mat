package com.ronrong.thymeleaf.mat.decorationstyle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum DecorationStyle {

    /** 标准 **/
    STANDARD("standard"),
    /** 基础风格 **/
    BASE("base"),
    /** 商业风格 **/
    BUSINESS("business"),
    /** 简单风格 **/
    SIMPLE("simple"),
    /** 测试 **/
    TEST("test");


    private static Logger logger = LoggerFactory.getLogger(DecorationStyle.class);

    private String style;

    DecorationStyle(String style) {
        this.style = style;
    }


    public static DecorationStyle parse(final String style) {
        if (style == null || style.trim().length() == 0) {
            throw new IllegalArgumentException("Template style cannot be null or empty");
        }
        if ("standard".equalsIgnoreCase(style)) {
            return STANDARD;
        }
        if ("base".equalsIgnoreCase(style)) {
            return BASE;
        }
        if ("business".equalsIgnoreCase(style)) {
            return BUSINESS;
        }
        if ("simple".equalsIgnoreCase(style)) {
            return SIMPLE;
        }
        if ("test".equalsIgnoreCase(style)) {
            return TEST;
        }
        
        return STANDARD;
    }


    public String getStyle() {
        return style;
    }
}
