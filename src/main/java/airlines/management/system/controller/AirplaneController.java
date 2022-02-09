package airlines.management.system.controller;

import java.util.List;
import java.util.stream.Collectors;
import airlines.management.system.dto.request.AirplaneRequestDto;
import airlines.management.system.dto.response.AirplaneResponseDto;
import airlines.management.system.model.Airplane;
import airlines.management.system.service.AirplaneService;
import airlines.management.system.service.mapper.impl.AirplaneMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/airplanes")
public class AirplaneController {
    private final AirplaneService airplaneService;
    private final AirplaneMapper airplaneMapper;

    @PostMapping
    public AirplaneResponseDto add(@RequestBody AirplaneRequestDto requestDto) {
        Airplane airplane = airplaneService.save(airplaneMapper.mapToModel(requestDto));
        return airplaneMapper.mapToDto(airplane);
    }

    @GetMapping
    public List<AirplaneResponseDto> getAll() {
        return airplaneService.getAll().stream()
                .map(airplaneMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AirplaneResponseDto get(@PathVariable Long id) {
        return airplaneMapper.mapToDto(airplaneService.get(id));
    }

    @PatchMapping("/{airplaneId}")
    public AirplaneResponseDto updateAirCompany(@PathVariable Long airplaneId,
                                             @RequestBody Long airCompanyId) {
        Airplane airplane = airplaneService.updateAirCompany(airplaneId, airCompanyId);
        return airplaneMapper.mapToDto(airplane);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        airplaneService.delete(id);
    }
}
