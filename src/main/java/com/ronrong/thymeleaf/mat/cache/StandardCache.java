package com.ronrong.thymeleaf.mat.cache;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.ronrong.thymeleaf.mat.util.Validate;
import org.slf4j.Logger;


/**
 */
public final class StandardCache<K, V> implements ICache<K,V> {

    
    private final String name;

    private final boolean traceExecution;
    private final Logger logger;


    private final ConcurrentHashMap<K, V> dataContainer = new ConcurrentHashMap<K, V>(20, 0.9f, 2);
    
    
    public StandardCache(final String name, final Logger logger) {
        
        super();

        Validate.notEmpty(name, "name 不能为空");

        this.name = name;

        this.logger = logger;
        this.traceExecution = (logger != null && logger.isTraceEnabled());
        
    }

    public void put(final K key, final V value) {

         this.dataContainer.put(key, value);


    }
    

    
    public V get(final K key) {
        return  this.dataContainer.get(key);
    }
    
    public Set<K> keySet() {
        return  this.dataContainer.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.dataContainer.values();
    }


    public void clear() {
        
        this.dataContainer.clear();
        
        if (this.traceExecution) {
            this.logger.trace(
                    "[ MAT ] 移除缓存{}的所有内容", new Object[] {  this.name});
        }
        
    }
    
    public void clearKey(final K key) {

        this.dataContainer.remove(key);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StandardCache{");
        sb.append("name='").append(name).append('\'');
        sb.append(", traceExecution=").append(traceExecution);
        sb.append(", dataContainer=").append(dataContainer);
        sb.append('}');
        return sb.toString();
    }
}
