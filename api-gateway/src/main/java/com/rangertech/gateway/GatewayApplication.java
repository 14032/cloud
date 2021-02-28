package com.rangertech.gateway;

import com.rangertech.gateway.filter.factory.SsoAuthGatewayFilterFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public SsoAuthGatewayFilterFactory ssoAuthGatewayFilterFactory(){
        return new SsoAuthGatewayFilterFactory();
    }
}
