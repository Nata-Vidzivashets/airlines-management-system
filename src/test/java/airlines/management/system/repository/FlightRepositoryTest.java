package airlines.management.system.repository;

import java.util.List;
import airlines.management.system.model.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FlightRepositoryTest {
    @Container
    static MySQLContainer<?> database = new MySQLContainer<>("mysql:8")
            .withDatabaseName("airline")
            .withPassword("airline")
            .withUsername("airline");

    @DynamicPropertySource
    static void setDataSourceProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url",database::getJdbcUrl);
        propertyRegistry.add("spring.datasource.password",database::getPassword);
        propertyRegistry.add("spring.datasource.username",database::getUsername);
    }

    @Autowired
    private FlightRepository flightRepository;

    @Test
    @Sql("/scripts/init_three_flights.sql")
    void shouldReturnFlightsByStatusIsCompletedAndFlightTimeIsBiggerThanEstimated() {
        List<Flight> actual = flightRepository.findAllByStatusCompletedAndEstimatedFlightTime();
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(1, actual.get(0).getId());
        Assertions.assertEquals(3, actual.get(1).getId());
    }
}
