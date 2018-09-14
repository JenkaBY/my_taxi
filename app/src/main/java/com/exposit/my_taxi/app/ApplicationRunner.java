package com.exposit.my_taxi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication //(exclude = {SecurityAutoConfiguration.class})
@ComponentScan("com.exposit.my_taxi")
@EntityScan("com.exposit.my_taxi.model")
@EnableJpaRepositories("com.exposit.my_taxi.repository")
@EnableCaching
public class ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
    }
}
