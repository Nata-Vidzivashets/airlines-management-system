package airlines.management.system.controller;

import java.util.List;
import java.util.stream.Collectors;
import airlines.management.system.dto.request.AirCompanyRequestDto;
import airlines.management.system.dto.response.AirCompanyResponseDto;
import airlines.management.system.model.AirCompany;
import airlines.management.system.service.AirCompanyService;
import airlines.management.system.service.mapper.impl.AirCompanyMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/air-companies")
public class AirCompanyController {
    private final AirCompanyService airCompanyService;
    private final AirCompanyMapper airCompanyMapper;

    @PostMapping
    public AirCompanyResponseDto add(@RequestBody AirCompanyRequestDto requestDto) {
        AirCompany airCompany = airCompanyService.save(airCompanyMapper.mapToModel(requestDto));
        return airCompanyMapper.mapToDto(airCompany);
    }

    @GetMapping
    public List<AirCompanyResponseDto> getAll() {
        return airCompanyService.getAll().stream()
                .map(airCompanyMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AirCompanyResponseDto get(@PathVariable Long id) {
        return airCompanyMapper.mapToDto(airCompanyService.get(id));
    }

    @PutMapping("/{id}")
    public AirCompanyResponseDto update(@PathVariable Long id,
                                        @RequestBody AirCompanyRequestDto requestDto) {
        AirCompany airCompany = airCompanyService.update(id, airCompanyMapper.mapToModel(requestDto));
        return airCompanyMapper.mapToDto(airCompany);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        airCompanyService.delete(id);
    }
}
