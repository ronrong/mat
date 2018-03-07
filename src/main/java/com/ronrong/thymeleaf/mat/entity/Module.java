package com.ronrong.thymeleaf.mat.entity;

import com.ronrong.thymeleaf.mat.util.Validate;

/**
 * @author:rongshaolin
 */
public class Module {

    private String codeFlag;

    private String name;

    private String description;

    private CommonEnum.DecoratorTypeEnum decoratorTypeEnum ;

    private int h;
    
    public CommonEnum.DecoratorTypeEnum getDecoratorTypeEnum() {
        return decoratorTypeEnum;
    }

    public Module(String name, CommonEnum.DecoratorTypeEnum decoratorTypeEnum, String codeFlag, String description) {
        this.codeFlag = codeFlag;
        this.name = name;
        this.description = description;
        this.decoratorTypeEnum = decoratorTypeEnum;
        this.h = computeHashCode();
    }

    public Module(String name, CommonEnum.DecoratorTypeEnum decoratorTypeEnum, String codeFlag) {
        this(name, decoratorTypeEnum, codeFlag,  null);
    }

    public void setDecoratorTypeEnum(CommonEnum.DecoratorTypeEnum decoratorTypeEnum) {

        Validate.notNull(decoratorTypeEnum, "decoratorTypeEnum 不能为空");
        this.decoratorTypeEnum = decoratorTypeEnum;
    }

    public String getCodeFlag() {
        return codeFlag;
    }

    public void setCodeFlag(String codeFlag) {
        this.codeFlag = codeFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Module)) {
            return false;
        }

        final Module that = (Module) o;

        if (this.h != that.h) {
            return false;
        }

        if (this.decoratorTypeEnum != null ? !this.decoratorTypeEnum.equals(that.decoratorTypeEnum) : that.decoratorTypeEnum != null) {
            return false;
        }

        if (this.codeFlag != null && !this.codeFlag.equals(that.codeFlag)) {
            return false;
        }
        if (this.name != null && !this.name.equals(that.name)) {
            return false;
        }
        if (this.description != null && !this.description.equals(that.description)) {
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
        result = 31 * result + (this.decoratorTypeEnum != null ? this.decoratorTypeEnum.hashCode() : 0);
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.codeFlag != null ? this.codeFlag.hashCode() : 0);
        result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
        return result;
    }




    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder();
        if (this.decoratorTypeEnum != null) {
            strBuilder.append("page");
            strBuilder.append("：");
            strBuilder.append(this.decoratorTypeEnum);
            strBuilder.append("   ");
        }

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
            strBuilder.append("   ");
        }
        if (this.name != null) {
            strBuilder.append("description");
            strBuilder.append("：");
            strBuilder.append(this.description);
        }
        return strBuilder.toString();
    }


}