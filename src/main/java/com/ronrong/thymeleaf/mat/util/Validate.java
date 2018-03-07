package com.ronrong.thymeleaf.mat.util;

import java.util.Collection;


public final class Validate {

    
    public static void notNull(final Object object, final String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void notEmpty(final String object, final String message) {
        if (StringUtils.isEmptyOrWhitespace(object)) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void notEmpty(final Collection<?> object, final String message) {
        if (object == null || object.size() == 0) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void notEmpty(final Object[] object, final String message) {
        if (object == null || object.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void containsNoNulls(final Iterable<?> collection, final String message) {
        for (final Object object : collection) {
            notNull(object, message);
        }
    }
    
    public static void containsNoEmpties(final Iterable<String> collection, final String message) {
        for (final String object : collection) {
            notEmpty(object, message);
        }
    }
    
    public static void containsNoNulls(final Object[] array, final String message) {
        for (final Object object : array) {
            notNull(object, message);
        }
    }
    
    public static void isTrue(final boolean condition, final String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
    
    
    private Validate() {
        super();
        
    }
}
