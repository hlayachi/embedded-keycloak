package com.embedded.keycloak.config;

import org.keycloak.models.KeycloakSession;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.services.managers.ApplianceBootstrap;
import org.keycloak.services.managers.RealmManager;
import org.keycloak.services.resources.KeycloakApplication;
import org.keycloak.util.JsonSerialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties(KeycloakServerProperties.class)
public class EmbeddedKeycloakApp extends KeycloakApplication {

    private static final Logger LOG = LoggerFactory.getLogger(EmbeddedKeycloakApp.class);

    static KeycloakServerProperties keycloakServerProperties;

    public EmbeddedKeycloakApp() {

        super();

        createMasterRealmAdminUser();

        createBaeldungRealm();
    }

    private void createMasterRealmAdminUser() {

        KeycloakSession session = getSessionFactory().create();

        ApplianceBootstrap applianceBootstrap = new ApplianceBootstrap(session);

        KeycloakServerProperties.AdminUser admin = keycloakServerProperties.getAdminUser();

        try {
            session.getTransactionManager().begin();
            applianceBootstrap.createMasterRealmUser(admin.getUsername(), admin.getPassword());
            session.getTransactionManager().commit();
        } catch (Exception ex) {
            LOG.warn("Couldn't create keycloak master admin user: {}", ex.getMessage());
            session.getTransactionManager().rollback();
        }

        session.close();
    }

    private void createBaeldungRealm() {
        KeycloakSession session = getSessionFactory().create();

        try {
            session.getTransactionManager().begin();

            RealmManager manager = new RealmManager(session);
            Resource lessonRealmImportFile = new ClassPathResource(keycloakServerProperties.getRealmImportFile());

            manager.importRealm(
                    JsonSerialization.readValue(lessonRealmImportFile.getInputStream(), RealmRepresentation.class));

            session.getTransactionManager().commit();
        } catch (Exception ex) {
            LOG.warn("Failed to import Realm json file: {}", ex.getMessage());
            session.getTransactionManager().rollback();
        }

        session.close();
    }
}
