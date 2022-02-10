package airlines.management.system.service.impl;

import airlines.management.system.model.Flight;
import airlines.management.system.model.enums.FlightStatus;
import airlines.management.system.repository.FlightRepository;
import airlines.management.system.service.FlightService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    @Override
    public Flight save(Flight flight) {
        if (flight.getStatus() == null) {
            flight.setStatus(FlightStatus.PENDING);
        }
        return flightRepository.save(flight);
    }

    @Override
    public Flight get(Long id) {
        return flightRepository.getById(id);
    }

    @Override
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> getAllByAirCompanyNameAndFlightStatus(String airCompanyName,
                                                              FlightStatus status) {
        return flightRepository.findAllByAirCompanyNameAndStatus(airCompanyName, status);
    }

    @Override
    public List<Flight> getAllByStatusActiveAndStartedAt24HoursAgo() {
        return flightRepository.findAllByStatusAndStartedAtBefore(FlightStatus.ACTIVE,
                LocalDateTime.now().minusDays(1L).withSecond(0).withNano(0));
    }

    @Override
    public Flight updateStatus(Long flightId, FlightStatus status) {
        Flight flight = flightRepository.getById(flightId);
        flight.setStatus(status);
        switch (status) {
            case ACTIVE:
                flight.setStartedAt(LocalDateTime.now().withSecond(0).withNano(0));
                break;
            case COMPLETED:
                flight.setEndedAt(LocalDateTime.now().withSecond(0).withNano(0));
                break;
            case DELAYED:
                flight.setDelayStartedAt(LocalDateTime.now().withSecond(0).withNano(0));
                break;
            case PENDING:
                break;
            default:
                throw new RuntimeException("Status does not exist: " + status);
        }
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getAllByStatusCompletedAndEstimatedFlightTime() {
        return flightRepository.findAllByStatusCompletedAndEstimatedFlightTime();
    }

    @Override
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }
}
