package com.whh.springboot.springdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class JwtTokenStoreConfig  {
    @Autowired
    private CustomJwtTokenConverter customJwtTokenConverter;
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //.\keytool -genkeypair -alias auth -keyalg RSA -keypass whhpass -keystore D:/keystore.jks -storepass whhpass
        // 导入证书
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "whhpass".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("auth"));
        converter.setAccessTokenConverter(customJwtTokenConverter);
        return converter;
    }

//    public CustomJwtTokenConverter converter(){
//        CustomJwtTokenConverter converter = new CustomJwtTokenConverter();
//        converter.setUserTokenConverter();
//    }
}
