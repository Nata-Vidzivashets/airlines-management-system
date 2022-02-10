package airlines.management.system.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import airlines.management.system.model.Flight;
import airlines.management.system.model.enums.FlightStatus;
import airlines.management.system.repository.FlightRepository;
import airlines.management.system.util.DateTimePatternUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {
    @InjectMocks
    private FlightServiceImpl flightService;
    private static Flight flight;
    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern(DateTimePatternUtil.DATE_TIME_PATTERN);

    @Mock
    private FlightRepository flightRepository;

    @BeforeAll
    public static void init() {
        flight = new Flight();
        flight.setId(1L);
        flight.setStatus(FlightStatus.PENDING);
    }

    @Test
    void shouldSaveAndSetStatusPending() {
        Flight flightWithoutStatus = new Flight();
        flightWithoutStatus.setId(1L);

        Mockito.when(flightRepository.save(flightWithoutStatus)).thenReturn(flightWithoutStatus);
        Flight actual = flightService.save(flightWithoutStatus);
        Assertions.assertEquals(flightWithoutStatus.getId(), actual.getId());
        Assertions.assertEquals("PENDING", actual.getStatus().toString());
    }

    @Test
    void shouldUpdateStatusActive() {
        Mockito.when(flightRepository.getById(1L)).thenReturn(flight);
        Mockito.when(flightRepository.save(flight)).thenReturn(flight);
        Flight actual = flightService.updateStatus(1L, FlightStatus.ACTIVE);
        Assertions.assertEquals(flight.getId(), actual.getId());
        Assertions.assertEquals(FlightStatus.ACTIVE, actual.getStatus());
        Assertions.assertEquals(LocalDateTime.now().format(dateTimeFormatter),
                actual.getStartedAt().format(dateTimeFormatter));
    }

    @Test
    void shouldUpdateStatusCompleted() {
        Mockito.when(flightRepository.getById(1L)).thenReturn(flight);
        Mockito.when(flightRepository.save(flight)).thenReturn(flight);
        Flight actual = flightService.updateStatus(1L, FlightStatus.COMPLETED);
        Assertions.assertEquals(flight.getId(), actual.getId());
        Assertions.assertEquals(FlightStatus.COMPLETED, actual.getStatus());
        Assertions.assertEquals(LocalDateTime.now().format(dateTimeFormatter),
                actual.getEndedAt().format(dateTimeFormatter));
    }

    @Test
    void shouldUpdateStatusDelayed() {
        Mockito.when(flightRepository.getById(1L)).thenReturn(flight);
        Mockito.when(flightRepository.save(flight)).thenReturn(flight);
        Flight actual = flightService.updateStatus(1L, FlightStatus.DELAYED);
        Assertions.assertEquals(flight.getId(), actual.getId());
        Assertions.assertEquals(FlightStatus.DELAYED, actual.getStatus());
        Assertions.assertEquals(LocalDateTime.now().format(dateTimeFormatter),
                actual.getDelayStartedAt().format(dateTimeFormatter));
    }

    @Test
    void shouldUpdateStatusPending() {
        flight.setStatus(FlightStatus.ACTIVE);
        Mockito.when(flightRepository.getById(1L)).thenReturn(flight);
        Mockito.when(flightRepository.save(flight)).thenReturn(flight);
        Flight actual = flightService.updateStatus(1L, FlightStatus.PENDING);
        Assertions.assertEquals(flight.getId(), actual.getId());
        Assertions.assertEquals(FlightStatus.PENDING, actual.getStatus());
    }
}
