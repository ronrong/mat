package com.ronrong.thymeleaf.mat.resourceresolver;

/**
 * @author:rongshaolin
 */
public enum ResourceType {
        /***
         *
         */
        FILE("file")
        /**
         *
         */
        ,DB("db")
        /**
         *
         */
        ,CLASS("class");


        private String name;

    ResourceType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static ResourceType parse(final String type) {

            if (type == null || type.trim().length() == 0) {
                throw new IllegalArgumentException("type cannot be null or empty");
            }
            if ("FILE".equalsIgnoreCase(type)) {
                return FILE;
            }
            if ("DB".equalsIgnoreCase(type)) {
                return DB;
            }

            if ("CLASS".equalsIgnoreCase(type)) {
                return CLASS;
            }

            throw new IllegalArgumentException("ResourceType not exist");
        }


}
