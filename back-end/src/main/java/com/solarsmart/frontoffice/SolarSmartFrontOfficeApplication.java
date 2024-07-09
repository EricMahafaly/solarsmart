package com.solarsmart.frontoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableRedisHttpSession
public class SolarSmartFrontOfficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolarSmartFrontOfficeApplication.class, args);
    }

}