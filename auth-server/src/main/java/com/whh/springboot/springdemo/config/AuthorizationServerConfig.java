package com.whh.springboot.springdemo.config;

import com.whh.springboot.springdemo.service.BaseUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private BaseUserDetailService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.authenticationManager(authenticationManager)
                // 配置JwtAccessToken转换器
                .accessTokenConverter(jwtAccessTokenConverter())
                // refresh_token需要userDetailsService
                .reuseRefreshTokens(false).userDetailsService(userDetailsService);
        //.tokenStore(getJdbcTokenStore());
    }
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        final JwtAccessTokenConverter converter = new JwtAccessToken();
        //.\keytool -genkeypair -alias auth -keyalg RSA -keypass whhpass -keystore D:/keystore.jks -storepass whhpass
        // 导入证书
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "whhpass".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("auth"));
        return converter;
    }
    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("admin")//配置client_id
                    .secret(passwordEncoder.encode("admin123456"))//配置client_secret
                    .accessTokenValiditySeconds(3600)//配置访问token的有效期
                    .refreshTokenValiditySeconds(864000)//配置刷新token的有效期
                    .redirectUris("http://www.baidu.com")//配置redirect_uri，用于授权成功后跳转
                    .scopes("all")//配置申请的权限范围
                    .authorizedGrantTypes("authorization_code","password");//配置grant_type，表示授权类型
        }
}
