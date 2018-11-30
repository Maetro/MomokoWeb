package com.momoko.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MomokoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MomokoApplication.class, args);
    }
}
