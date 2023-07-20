package com.example.window.repo;

import com.example.window.model.AddressView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.support.WindowIterator;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/scripts/usersData.sql")
class UserRepositoryTest {

    @Container
    static PostgreSQLContainer database = new PostgreSQLContainer("postgres:14")
            .withDatabaseName("userRepoDB")
            .withUsername("testuser")
            .withPassword("testpassword");

    @Autowired
    UserRepository userRepository;

    @DynamicPropertySource
    static void setDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
    }

    @Test
    void testWindow() {
        WindowIterator<AddressView> iterator = WindowIterator.of(userRepository::findFirst20By).startingAt(ScrollPosition.keyset());
        int totalOfElements = 0;
        while (iterator.hasNext()){
            final var next = iterator.next();
            totalOfElements++;
        }
        assertEquals(30, totalOfElements);

    }

}