package com.embedded.keycloak.resource;

import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResource {

    private final RealmResource etradeRealm;

    public UserResource(RealmResource etradeRealm) {
        this.etradeRealm = etradeRealm;
    }

    @GetMapping("/users")
    public String findOn() {
        List<UserRepresentation> users = etradeRealm.users().list();
        return users.stream().findFirst().map(u -> u.getUsername()).orElse("default");
    }
}
