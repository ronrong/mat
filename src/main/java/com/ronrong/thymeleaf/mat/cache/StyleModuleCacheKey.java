package com.ronrong.thymeleaf.mat.cache;

import java.io.Serializable;
import com.ronrong.thymeleaf.mat.decorationstyle.DecorationStyle;
import com.ronrong.thymeleaf.mat.entity.Module;


/**
 * <p>
 *   这个类模型对象在模板缓存中用作键。
 * </p>
 */
public final class StyleModuleCacheKey implements Serializable {

    private static final long serialVersionUID = 1519454097L;

    private DecorationStyle decorationStyle;
    private Module module;

    private int h;

    public StyleModuleCacheKey(DecorationStyle decorationStyle, Module module) {
        this.decorationStyle = decorationStyle;
        this.module = module;
        this.h = computeHashCode();
    }



    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof StyleModuleCacheKey)) {
            return false;
        }

        final StyleModuleCacheKey that = (StyleModuleCacheKey) o;

        if (this.h != that.h) {
            return false;
        }

        if (this.decorationStyle != null ? !this.decorationStyle.equals(that.decorationStyle) : that.decorationStyle != null) {
            return false;
        }

        if (this.module != null ? !this.module.equals(that.module) : that.module != null) {
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
        result = 31 * result + (this.decorationStyle != null ? this.decorationStyle.hashCode() : 0);
        result = 31 * result + (this.module != null ? this.module.hashCode() : 0);
        return result;
    }




    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder();
        if (this.decorationStyle != null) {
            strBuilder.append("decorationStyle");
            strBuilder.append("：");
            strBuilder.append(this.decorationStyle);
            strBuilder.append("   ");

        }

        if (this.module != null) {
            strBuilder.append("module");
            strBuilder.append("：");
            strBuilder.append(this.module);
        }
        return strBuilder.toString();
    }
}
