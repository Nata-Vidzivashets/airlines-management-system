package airlines.management.system.service.mapper.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import airlines.management.system.dto.request.AirplaneRequestDto;
import airlines.management.system.dto.response.AirplaneResponseDto;
import airlines.management.system.model.Airplane;
import airlines.management.system.model.enums.AirplaneType;
import airlines.management.system.service.AirCompanyService;
import airlines.management.system.service.mapper.RequestDtoMapper;
import airlines.management.system.service.mapper.ResponseDtoMapper;
import airlines.management.system.util.DateTimePatternUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AirplaneMapper implements RequestDtoMapper<AirplaneRequestDto, Airplane>,
        ResponseDtoMapper<AirplaneResponseDto, Airplane> {
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(DateTimePatternUtil.DATE_PATTERN);
    private final AirCompanyService airCompanyService;

    @Override
    public Airplane mapToModel(AirplaneRequestDto dto) {
        Airplane airplane = new Airplane();
        airplane.setName(dto.getName());
        airplane.setSerialNumber(dto.getSerialNumber());
        airplane.setAirCompany(airCompanyService.get(dto.getAirCompanyId()));
        airplane.setAmountFlights(dto.getAmountFlights());
        airplane.setFlightDistance(dto.getFlightDistance());
        airplane.setFuelCapacity(dto.getFuelCapacity());
        airplane.setType(AirplaneType.valueOf(dto.getType()));
        airplane.setCreatedAt(LocalDate.parse(dto.getCreatedAt(), formatter));
        return airplane;
    }

    @Override
    public AirplaneResponseDto mapToDto(Airplane airplane) {
        AirplaneResponseDto responseDto = new AirplaneResponseDto();
        responseDto.setId(airplane.getId());
        responseDto.setName(airplane.getName());
        responseDto.setSerialNumber(airplane.getSerialNumber());
        responseDto.setAirCompanyId(airplane.getAirCompany().getId());
        responseDto.setAmountFlights(airplane.getAmountFlights());
        responseDto.setFlightDistance(airplane.getFlightDistance());
        responseDto.setFuelCapacity(airplane.getFuelCapacity());
        responseDto.setType(airplane.getType().toString());
        responseDto.setCreatedAt(airplane.getCreatedAt().format(formatter));
        return responseDto;
    }
}
