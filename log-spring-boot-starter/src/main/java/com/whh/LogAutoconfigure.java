package com.whh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(LogService.class)
@EnableConfigurationProperties(LogProperties.class)
public class LogAutoconfigure {
    @Autowired
    private LogProperties kiteProperties;
    @Bean
    @ConditionalOnMissingBean(LogService.class)
    @ConditionalOnProperty(prefix = "log.auto",value = "enabled", havingValue = "true")
    LogService logService(){
        return new LogService(kiteProperties);
    }
}
