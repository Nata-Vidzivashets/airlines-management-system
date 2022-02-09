package airlines.management.system.controller;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import airlines.management.system.dto.request.FlightRequestDto;
import airlines.management.system.dto.response.FlightResponseDto;
import airlines.management.system.model.Flight;
import airlines.management.system.model.enums.FlightStatus;
import airlines.management.system.service.FlightService;
import airlines.management.system.service.mapper.impl.FlightMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;
    private final FlightMapper flightMapper;

    @PostMapping
    public FlightResponseDto add(@RequestBody FlightRequestDto requestDto) {
        Flight flight = flightService.save(flightMapper.mapToModel(requestDto));
        return flightMapper.mapToDto(flight);
    }

    @GetMapping
    public List<FlightResponseDto> getAll() {
        return flightService.getAll().stream()
                .map(flightMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FlightResponseDto get(@PathVariable Long id) {
        return flightMapper.mapToDto(flightService.get(id));
    }

    @GetMapping("/available-company")
    public List<FlightResponseDto> getAllByAirCompanyNameAndFlightStatus(
            @RequestParam(value = "company-name") String airCompanyName,
            @RequestParam String status) {
        return flightService.getAllByAirCompanyNameAndFlightStatus(airCompanyName,
                        FlightStatus.valueOf(status)).stream()
                .map(flightMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/active")
    public List<FlightResponseDto> getAllByStatusActiveAndStartedAt24HoursAgo() {
        return flightService.getAllByStatusActiveAndStartedAt24HoursAgo().stream()
                .map(flightMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    public FlightResponseDto updateStatus(@PathVariable Long id,
                                          @RequestBody String status) {
        Flight flight = flightService.updateStatus(id, FlightStatus.valueOf(status.toUpperCase(Locale.ROOT)));
        return flightMapper.mapToDto(flight);
    }

    @GetMapping("/available-flight-time")
    public List<FlightResponseDto> getAllByStatusCompletedAndEstimatedFlightTime() {
        return flightService.getAllByStatusCompletedAndEstimatedFlightTime().stream()
                .map(flightMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        flightService.delete(id);
    }
}
