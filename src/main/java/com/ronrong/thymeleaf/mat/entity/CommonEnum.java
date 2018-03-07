/**
 * @File:PageType.java
 * @Copyright:Copyright(c)2017
 * @Company:ronrong
 * @author:rongshaolin
 * @create 2018-02-06 17:03
 **/

package com.ronrong.thymeleaf.mat.entity;

/**
 * @Description:TODO
 * @author:rongshaolin
 * @date:2018-02-06 17:03
 */
public class CommonEnum {



    /**
     * 页面类型
     */
    public enum PageTypeEnum {
        /***
         * 静态，不重组
         */
        STATIC()
        /**
         * 动态重组
         */
        ,DYNAMIC();

        PageTypeEnum() {
        }

    }



    /**
     * 装修工工种
     */
    public enum DecoratorTypeEnum {
        /***
         *
         */
        HEADER("头信息", "header")
        /***
         *
         */
        ,FOOTER("尾信息", "footer")
        /***
         *
         */
        ,BOTTOMNAVBAR("底部导航", "bottomNavBar")
        /**
         *
         */
        ,SLIDE("轮播", "slide")
        /**
         *
         */
        ,AD("广告", "ad")
        /**
         *
         */
        ,TITLE("标题", "title")
        /**
         *
         */
        ,TITLEHEADER("顶部标题", "titleHeader")
        /**
         *
         */
        ,TITLENAV("导航标题", "titleNav")
        /**
         *
         */
        ,PRODUCT("产品", "product");



        private String name;
        private String moduleFlag;

        DecoratorTypeEnum(String name, String moduleFlag) {
            this.name = name;
            this.moduleFlag = moduleFlag;
        }

        public String getName() {
            return name;
        }

        public String getModuleFlag() {
            return moduleFlag;
        }

        public static DecoratorTypeEnum parse(final String type) {

            if (type == null || type.trim().length() == 0) {
                throw new IllegalArgumentException("decorator type cannot be null or empty");
            }
            if ("SLIDE".equalsIgnoreCase(type)) {
                return SLIDE;
            }
            if ("AD".equalsIgnoreCase(type)) {
                return AD;
            }

            throw new IllegalArgumentException("decorator type not exist");
        }


    }
}