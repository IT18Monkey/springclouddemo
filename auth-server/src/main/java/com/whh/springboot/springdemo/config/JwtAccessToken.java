package com.whh.springboot.springdemo.config;

import com.whh.springboot.springdemo.domain.BaseUserDetail;
import com.whh.springboot.springdemo.dto.BaseUser;
import com.whh.springboot.springdemo.util.JsonUtil;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

public class JwtAccessToken extends JwtAccessTokenConverter {
    public static String USER_INFO = "USER_INFO";

    /**
     * 生成token
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);

        // 设置额外用户信息
        BaseUser baseUser = ((BaseUserDetail) authentication.getPrincipal()).getBaseUser();
        baseUser.setPassword(null);
        // 将用户信息添加到token额外信息中
        defaultOAuth2AccessToken.getAdditionalInformation().put(USER_INFO, baseUser);

        return super.enhance(defaultOAuth2AccessToken, authentication);
    }

    /**
     * 解析token
     * @param value
     * @param map
     * @return
     */
    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map){
        OAuth2AccessToken oauth2AccessToken = super.extractAccessToken(value, map);
        convertData(oauth2AccessToken, oauth2AccessToken.getAdditionalInformation());
        return oauth2AccessToken;
    }

    private void convertData(OAuth2AccessToken accessToken,  Map<String, ?> map) {
        accessToken.getAdditionalInformation().put(USER_INFO,convertUserData(map.get(USER_INFO)));
    }

    private BaseUser convertUserData(Object map) {
        String json = JsonUtil.objectToString(map);
        BaseUser user = JsonUtil.jsonToObject(json, BaseUser.class);
        return user;
    }
}
