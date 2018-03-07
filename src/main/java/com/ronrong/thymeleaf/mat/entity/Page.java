package com.ronrong.thymeleaf.mat.entity;

import com.ronrong.thymeleaf.mat.decoration.AbstractDecorator;

import java.util.Map;
import java.util.HashMap;

/**
 * @Description: 一个页面由多个模块模板组成
 * @author:rongshaolin
 */
public class Page {

    private String name;
    private String codeFlag;

    private int h;

    public Page(String name, String codeFlag) {
        this.name = name;
        this.codeFlag = codeFlag;
        this.h = computeHashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeFlag() {
        return codeFlag;
    }

    public void setCodeFlag(String codeFlag) {
        this.codeFlag = codeFlag;
    }


    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Page)) {
            return false;
        }

        final Page that = (Page) o;

        if (this.h != that.h) {
            return false;
        }

        if (this.codeFlag !=null && !this.codeFlag.equals(that.codeFlag)) {
            return false;
        }
        if (this.name !=null && !this.name.equals(that.name)) {
            return false;
        }
        return true;

    }


    @Override
    public int hashCode() {
        return this.h;
    }


    private int computeHashCode() {
        int result = 0;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.codeFlag != null ? this.codeFlag.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder();

        if (this.name != null) {
            strBuilder.append("name");
            strBuilder.append("：");
            strBuilder.append(this.name);
            strBuilder.append("   ");
        }
        if (this.name != null) {
            strBuilder.append("codeFlag");
            strBuilder.append("：");
            strBuilder.append(this.codeFlag);
        }
        return strBuilder.toString();
    }

}