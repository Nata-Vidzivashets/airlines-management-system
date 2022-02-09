package airlines.management.system.service;

import java.util.List;
import airlines.management.system.model.Flight;
import airlines.management.system.model.enums.FlightStatus;

public interface FlightService {
    Flight save(Flight flight);

    Flight get(Long id);

    List<Flight> getAll();

    List<Flight> getAllByAirCompanyNameAndFlightStatus(String airCompanyName, FlightStatus status);

    List<Flight> getAllByStatusActiveAndStartedAt24HoursAgo();

    Flight updateStatus(Long flightId, FlightStatus status);

    List<Flight> getAllByStatusCompletedAndEstimatedFlightTime();

    void delete(Long id);
}
