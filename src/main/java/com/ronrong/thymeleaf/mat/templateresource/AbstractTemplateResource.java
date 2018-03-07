
package com.ronrong.thymeleaf.mat.templateresource;

import com.ronrong.thymeleaf.mat.entity.ITemplate;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author:rongshaolin
 */
public abstract class AbstractTemplateResource implements IDecorationResource {

    /**
     * <p>
     * 返回可用于使用模板内容的字符串。
     * </p>
     */
    @Override
    public Object content() throws IOException {

        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = (BufferedReader)reader();
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return result.toString();
    }




}