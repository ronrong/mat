package com.ronrong.thymeleaf.mat.templateresource;

import java.io.IOException;
import java.io.Reader;

/**
 * <hr/>
 * <p>
 *   由所有<em>模板资源</em>实例实现的接口。
 * </p>
 *
 */
public interface IDecorationResource {


    public String getDescription();


    /**
     * <p>
     *  返回资源的基本名称。
     * </p>
     */
    public String getBaseName();


    /**
     * <p>
     *  确定由此对象表示的资源是否确实存在或不存在。
     * </p>
     */
    public boolean exists();


    /**
     * <p>
     *  资源返回的内容。
     * </p>
     */
    public Object content() throws IOException;


    /**
     * 返回可用于使用模板内容的{@link Reader}。
     * @throws IOException
     */
    public Reader reader() throws IOException;


    /**
     * <p>
     *
     * </p>
     */
    public IDecorationResource relative(final String relativeLocation);

}
