package com.embedded.keycloak.resource;

import com.embedded.keycloak.EmbeddedKeycloakApplication;
import com.embedded.keycloak.config.EmbeddedKeycloakApp;
import com.embedded.keycloak.config.EmbeddedKeycloakConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmbeddedKeycloakApplication.class, EmbeddedKeycloakConfig.class}, webEnvironment = DEFINED_PORT)
@AutoConfigureMockMvc
class UserResourceTest {

    @Autowired
    MockMvc mvc;

    @Test
    void findOn() throws Exception {
        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("john@test.com"));
    }
}