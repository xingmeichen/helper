package com.mabel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableZuulProxy
@EnableDiscoveryClient
@EnableSwagger2
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public HelperFilter helperFilter() {
		return new HelperFilter();
	}

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route("path_routes", r -> r.path("/get").uri("http://httpbin.ogr"))
//				.build();
//	}

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route("path_route", r -> r.path("/get")
//						.uri("http://httpbin.org"))
//				.route("host_route", r -> r.host("*.myhost.org")
//						.uri("http://httpbin.org"))
//				.route("rewrite_route", r -> r.host("*.rewrite.org")
//						.filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
//						.uri("http://httpbin.org"))
//				.route("hystrix_route", r -> r.host("*.hystrix.org")
//						.filters(f -> f.hystrix(c -> c.setName("slowcmd")))
//						.uri("http://httpbin.org"))
//				.route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
//						.filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
//						.uri("http://httpbin.org"))
////				.route("limit_route", r -> r
////						.host("*.limited.org").and().path("/anything/**")
////						.filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
////						.uri("http://httpbin.org"))
//				.build();
//	}

}
