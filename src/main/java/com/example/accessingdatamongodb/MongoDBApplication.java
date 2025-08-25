package com.example.accessingdatamongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
Application entry point.
*/
@SpringBootApplication
public final class MongoDBApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MongoDBApplication.class, args);
    }

    private MongoDBApplication() {
    }
}
