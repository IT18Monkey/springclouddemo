package com.whh.springboot.springdemo.config;

import com.whh.springboot.springdemo.domain.BaseUserDetail;
import com.whh.springboot.springdemo.dto.BaseUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenEnhancer implements TokenEnhancer {
    public static String USER_INFO = "userInfo";
    /*
     token里面添加额外信息
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        // 设置额外用户信息
        BaseUser baseUser = ((BaseUserDetail) authentication.getPrincipal()).getBaseUser();
        baseUser.setPassword(null);
        Map<String, Object> info = new HashMap<>();
        info.put(USER_INFO, baseUser);
        // 将用户信息添加到token额外信息中
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
