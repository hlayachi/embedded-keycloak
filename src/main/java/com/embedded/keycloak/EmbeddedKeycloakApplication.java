package com.embedded.keycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;

@SpringBootApplication
public class EmbeddedKeycloakApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmbeddedKeycloakApplication.class, args);
    }

}
