package com.web.yapp.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class YappApplication {

    public static void main(String[] args) {
        SpringApplication.run(YappApplication.class, args);
    }

}
