package com.embedded.keycloak.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@ConfigurationProperties("keycloak-props")
@Component
public class KeycloakConfig {
    private String realm;
    private String masterRealm;
    private String authServerUrl;
    private String resource;
    private String username;
    private String password;

    @Bean
    public Keycloak keycloakFactory(){
        return KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .realm(masterRealm)
                .clientId(resource)
                .username(username)
                .password(password)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();
    }

    @Bean
    public RealmResource etradeResource(Keycloak keycloak){
        return keycloak.realm(realm);
    }

    public String getRealm() {
        return realm;
    }

    public KeycloakConfig setRealm(String realm) {
        this.realm = realm;
        return this;
    }

    public String getMasterRealm() {
        return masterRealm;
    }

    public KeycloakConfig setMasterRealm(String masterRealm) {
        this.masterRealm = masterRealm;
        return this;
    }

    public String getAuthServerUrl() {
        return authServerUrl;
    }

    public KeycloakConfig setAuthServerUrl(String authServerUrl) {
        this.authServerUrl = authServerUrl;
        return this;
    }

    public String getResource() {
        return resource;
    }

    public KeycloakConfig setResource(String resource) {
        this.resource = resource;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public KeycloakConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public KeycloakConfig setPassword(String password) {
        this.password = password;
        return this;
    }
}
