package com.company.config.persistence;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("spring.datasource")
public class DatabaseConfig {

    private String type;
    private String url;
    private String username;
    private String password;
    private String platform;

}
