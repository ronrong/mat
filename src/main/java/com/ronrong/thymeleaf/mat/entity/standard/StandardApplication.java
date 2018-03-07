package com.ronrong.thymeleaf.mat.entity.standard;
import java.io.Serializable;

public class StandardApplication implements Serializable {


    private String jsver;
    private String domainssl;

    public String getJsver() {
        return jsver;
    }

    public void setJsver(String jsver) {
        this.jsver = jsver;
    }

    public String getDomainssl() {
        return domainssl;
    }

    public void setDomainssl(String domainssl) {
        this.domainssl = domainssl;
    }
}