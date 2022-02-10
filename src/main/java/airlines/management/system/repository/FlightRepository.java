package airlines.management.system.repository;

import airlines.management.system.model.Flight;
import airlines.management.system.model.enums.FlightStatus;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findAllByAirCompanyNameAndStatus(String airCompanyName, FlightStatus status);

    List<Flight> findAllByStatusAndStartedAtBefore(FlightStatus status, LocalDateTime dateTime);

    @Query(value = "SELECT * FROM flights f "
            + "WHERE f.status = 'COMPLETED' "
            + "AND TIMESTAMPDIFF(MINUTE, f.started_at, f.ended_at) > estimated_flight_time",
            nativeQuery = true)
    List<Flight> findAllByStatusCompletedAndEstimatedFlightTime();
}
