package airlines.management.system.service.mapper.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import airlines.management.system.dto.request.FlightRequestDto;
import airlines.management.system.dto.response.FlightResponseDto;
import airlines.management.system.model.Airplane;
import airlines.management.system.model.Flight;
import airlines.management.system.model.enums.FlightStatus;
import airlines.management.system.service.AirCompanyService;
import airlines.management.system.service.AirplaneService;
import airlines.management.system.service.mapper.RequestDtoMapper;
import airlines.management.system.service.mapper.ResponseDtoMapper;
import airlines.management.system.util.DateTimePatternUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FlightMapper implements RequestDtoMapper<FlightRequestDto, Flight>,
        ResponseDtoMapper<FlightResponseDto, Flight> {
    private final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern(DateTimePatternUtil.DATE_PATTERN);
    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern(DateTimePatternUtil.DATE_TIME_PATTERN);
    private final AirCompanyService airCompanyService;
    private final AirplaneService airplaneService;

    @Override
    public Flight mapToModel(FlightRequestDto dto) {
        Flight flight = new Flight();
        if (dto.getStatus() != null) {
            flight.setStatus(FlightStatus.valueOf(dto.getStatus()));
        }
        flight.setAirCompany(airCompanyService.get(dto.getAirCompanyId()));
        flight.setAirplanes(dto.getAirplanesId().stream()
                .map(airplaneService::get)
                .collect(Collectors.toList()));
        flight.setDepartureCountry(dto.getDepartureCountry());
        flight.setDestinationCountry(dto.getDestinationCountry());
        flight.setDistance(dto.getDistance());
        flight.setEstimatedFlightTime(dto.getEstimatedFlightTime());
        flight.setStartedAt(LocalDateTime.parse(dto.getStartedAt(), dateTimeFormatter));
        flight.setEndedAt(LocalDateTime.parse(dto.getEndedAt(), dateTimeFormatter));
        flight.setDelayStartedAt(LocalDateTime.parse(dto.getDelayStartedAt(), dateTimeFormatter));
        flight.setCreatedAt(LocalDate.parse(dto.getCreatedAt(), dateFormatter));
        return flight;
    }

    @Override
    public FlightResponseDto mapToDto(Flight flight) {
        FlightResponseDto responseDto = new FlightResponseDto();
        responseDto.setId(flight.getId());
        responseDto.setStatus(flight.getStatus().toString());
        responseDto.setAirCompanyId(flight.getAirCompany().getId());
        responseDto.setAirplanesId(flight.getAirplanes().stream()
                .map(Airplane::getId)
                .collect(Collectors.toList()));
        responseDto.setDepartureCountry(flight.getDepartureCountry());
        responseDto.setDestinationCountry(flight.getDestinationCountry());
        responseDto.setDistance(flight.getDistance());
        responseDto.setEstimatedFlightTime(flight.getEstimatedFlightTime());
        responseDto.setStartedAt(flight.getStartedAt().format(dateTimeFormatter));
        responseDto.setEndedAt(flight.getEndedAt().format(dateTimeFormatter));
        responseDto.setDelayStartedAt(flight.getDelayStartedAt().format(dateTimeFormatter));
        responseDto.setCreatedAt(flight.getCreatedAt().format(dateFormatter));
        return responseDto;
    }
}
