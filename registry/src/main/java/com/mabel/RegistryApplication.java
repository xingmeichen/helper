package com.mabel;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaServer
public class RegistryApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(RegistryApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
//		SpringApplication.run(RegistryApplication.class, args);
	}
}
