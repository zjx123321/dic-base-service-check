package com.jdddata.dic.base.service.check.muti.source.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhengjiexiang on 2018/12/20
 */
@Configuration
public class BusinessConfig {

    @Value("${business.check.soccer.mutiSource}")
    private String mutiSource;

    @Value("${business.check.basket.mutiSource}")
    private String basketMutiSource;

    @Value("${business.check.soccer.headList}")
    private String headList;

    @Value("${business.check.basket.headList}")
    private String basketHeadList;

    public String getMutiSource() {
        return mutiSource;
    }

    public void setMutiSource(String mutiSource) {
        this.mutiSource = mutiSource;
    }

    public String getBasketMutiSource() {
        return basketMutiSource;
    }

    public void setBasketMutiSource(String basketMutiSource) {
        this.basketMutiSource = basketMutiSource;
    }

    public String getHeadList() {
        return headList;
    }

    public void setHeadList(String headList) {
        this.headList = headList;
    }

    public String getBasketHeadList() {
        return basketHeadList;
    }

    public void setBasketHeadList(String basketHeadList) {
        this.basketHeadList = basketHeadList;
    }
}
