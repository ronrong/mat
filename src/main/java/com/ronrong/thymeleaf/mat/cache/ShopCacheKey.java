package com.ronrong.thymeleaf.mat.cache;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;
import com.ronrong.thymeleaf.mat.entity.Module;
import com.ronrong.thymeleaf.mat.entity.Shop;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.util.LoggingUtils;
import org.thymeleaf.util.Validate;


public final class ShopCacheKey implements Serializable {

    private static final long serialVersionUID = 1519454597L;

    private Shop shop;

    private int h;

    public ShopCacheKey(Shop shop) {
        this.shop = shop;
        this.h = computeHashCode();
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof ShopCacheKey)) {
            return false;
        }

        final ShopCacheKey that = (ShopCacheKey) o;

        if (this.h != that.h) {
            return false;
        }

        if (this.shop != null ? !this.shop.equals(that.shop) : that.shop != null) {
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
        result = 31 * result + (this.shop != null ? this.shop.hashCode() : 0);
        return result;
    }




    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder();
        if (this.shop != null) {
            strBuilder.append("shop");
            strBuilder.append("：");
            strBuilder.append(this.shop);
        }

        return strBuilder.toString();
    }

}
