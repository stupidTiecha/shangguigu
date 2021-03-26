package com.tiehca.apitest.heshang.common.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chen
 */
@Component
@ConfigurationProperties(prefix = "image.url")
public class ServiceConfig {

    private String imageUrlPrefix;

    public String getImageUrlPrefix() {
        return imageUrlPrefix;
    }


    public void setImageUrlPrefix(String imageUrlPrefix) {
        this.imageUrlPrefix = imageUrlPrefix;
    }
}
