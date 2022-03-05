package com.whh;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("log.auto")
@Data
public class LogProperties {
    private String prefix;
}
