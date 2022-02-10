package airlines.management.system.service.mapper.impl;

import airlines.management.system.dto.request.AirCompanyRequestDto;
import airlines.management.system.dto.response.AirCompanyResponseDto;
import airlines.management.system.model.AirCompany;
import airlines.management.system.model.enums.AirCompanyType;
import airlines.management.system.service.mapper.RequestDtoMapper;
import airlines.management.system.service.mapper.ResponseDtoMapper;
import airlines.management.system.util.DateTimePatternUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AirCompanyMapper implements RequestDtoMapper<AirCompanyRequestDto, AirCompany>,
        ResponseDtoMapper<AirCompanyResponseDto, AirCompany> {
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(DateTimePatternUtil.DATE_PATTERN);

    @Override
    public AirCompany mapToModel(AirCompanyRequestDto dto) {
        AirCompany airCompany = new AirCompany();
        airCompany.setName(dto.getName());
        airCompany.setType(AirCompanyType.valueOf(dto.getType()));
        airCompany.setFoundedAt(LocalDate.parse(dto.getFoundedAt(), formatter));
        return airCompany;
    }

    @Override
    public AirCompanyResponseDto mapToDto(AirCompany airCompany) {
        AirCompanyResponseDto responseDto = new AirCompanyResponseDto();
        responseDto.setId(airCompany.getId());
        responseDto.setName(airCompany.getName());
        responseDto.setType(airCompany.getType().toString());
        responseDto.setFoundedAt(airCompany.getFoundedAt().format(formatter));
        return responseDto;
    }
}
