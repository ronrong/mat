package com.ronrong.thymeleaf.mat.decoration;

import com.ronrong.thymeleaf.mat.IDecorationConfiguration;
import com.ronrong.thymeleaf.mat.cache.ICacheManager;

/**
 * @Description: 装修中介
 * @author:rongshaolin
 * @date:2018-02-07 13:47
 */
public interface IDecorationAgent {

    /**
     * <p>
     * 获得装修公司
     * </p>
     */
    public IDecorationCompany getDecorationCompany();

    /**
     * 获得配置
     * @return
     */
    public IDecorationConfiguration getConfiguration();


    /**
     * 缓存管理
     * @return
     */
    public ICacheManager getCacheManager();



}