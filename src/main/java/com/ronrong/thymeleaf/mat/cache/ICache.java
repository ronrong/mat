
package com.ronrong.thymeleaf.mat.cache;


import java.util.Collection;
import java.util.Set;


public interface ICache<K, V> {


    public void put(final K key, final V value);
    

    public V get(final K key);
    

    public void clear();
    

    public void clearKey(final K key);


    public Set<K> keySet();

    public Collection<V> values();


}
