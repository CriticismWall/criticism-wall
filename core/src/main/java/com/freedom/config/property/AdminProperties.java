package com.freedom.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 后台管理配置
 *
 * @author yu
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "global.admin")
public class AdminProperties {

    private String username;
    private String password;
    private String nickname;
    private String defaultRole;
}
