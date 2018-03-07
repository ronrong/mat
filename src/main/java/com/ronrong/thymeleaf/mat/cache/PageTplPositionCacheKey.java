
package com.ronrong.thymeleaf.mat.cache;

import com.ronrong.thymeleaf.mat.entity.Page;
import com.ronrong.thymeleaf.mat.util.Validate;

/**
 * @Description:页面和模板联合缓存Key，用于
 * @author:rongshaolin
 */
public class PageTplPositionCacheKey {

    private Page page;
    private String tplPosition;
    private final int h;

    public PageTplPositionCacheKey(Page page, String tplPosition) {

        Validate.notNull(page, " page 不能为空");
        Validate.notNull(tplPosition, " tplPosition 不能为空");

        this.page = page;
        this.tplPosition = tplPosition;
        // 防止重复计算hashcode
        this.h = computeHashCode();

    }



    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof PageTplPositionCacheKey)) {
            return false;
        }

        final PageTplPositionCacheKey that = (PageTplPositionCacheKey) o;

        if (this.h != that.h) {
            return false;
        }

        if (this.page != null ? !this.page.equals(that.page) : that.page != null) {
            return false;
        }
        if (!this.tplPosition.equals(that.tplPosition)) {
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
        result = 31 * result + (this.page != null ? this.page.hashCode() : 0);
        result = 31 * result + (this.tplPosition != null ? this.tplPosition.hashCode() : 0);
        return result;
    }




    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder();
        if (this.page != null) {
            strBuilder.append("page");
            strBuilder.append("：");
            strBuilder.append(this.page);
            strBuilder.append("   ");
        }

        if (this.tplPosition != null) {
            strBuilder.append("tplPosition");
            strBuilder.append("：");
            strBuilder.append(this.tplPosition);
        }
        return strBuilder.toString();
    }


}