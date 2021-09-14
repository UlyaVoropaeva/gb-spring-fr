package ru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:/META-INF/hibernate.cfg.xml")
public class FirstSpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(FirstSpringBootApp.class, args);
    }
}