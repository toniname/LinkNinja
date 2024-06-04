package com.applink.app.configuration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@DirtiesContext
@PropertySource("classpath:application.properties")
public abstract class IntegrationTestsDatabase {

    @Container
    private static final PostgreSQLContainer<?> container = new
            PostgreSQLContainer<>("postgres:16")
            .withUsername("postgres")
            .withPassword("postgres");

    @BeforeAll
    public static void runContainer() {
        container.start();
    }

    /**
     * Using dynamic properties to get database url for tests
     * */
    @DynamicPropertySource
    public static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.flyway.url", container::getJdbcUrl);
    }


    @AfterAll
    public static void stopContainer() {
        container.stop();
    }
}
