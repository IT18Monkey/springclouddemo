package com.whh.springboot.springdemo.config;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomJwtTokenConverter extends DefaultAccessTokenConverter {

    /**
     * @param claims 将解析好的JWT数据存放到OAuth2Authentication中的Details中
     *               让所有请求都能更加方便获取JWT自定义数据
     * @return
     */
    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        OAuth2Authentication authentication
                = super.extractAuthentication(claims);
        authentication.setDetails(claims);
        return authentication;
    }

}