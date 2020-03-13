package com.mabel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @project:
 * @description:
 * @author: Mabel.Chen
 * @create: 2020/3/12
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableConfigServer
//@EnableDiscoveryClient
public class ConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationApplication.class, args);
    }
}
