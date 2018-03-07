package com.ronrong.thymeleaf.mat.util;

public final class ClassLoaderUtils {
    
    
    public static ClassLoader getClassLoader(final Class<?> clazz) {
        final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            return contextClassLoader;
        }
        if (clazz != null) {
            final ClassLoader clazzClassLoader = clazz.getClassLoader();
            if (clazzClassLoader != null) {
                return clazzClassLoader;
            }
        }
        return ClassLoader.getSystemClassLoader();
    }
    
    
    
    private ClassLoaderUtils() {
        super();
    }
    
    
    
}
