package airlines.management.system.service.impl;

import java.util.List;
import airlines.management.system.model.Airplane;
import airlines.management.system.repository.AirCompanyRepository;
import airlines.management.system.repository.AirplaneRepository;
import airlines.management.system.service.AirplaneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AirplaneServiceImpl implements AirplaneService {
    private final AirplaneRepository airplaneRepository;
    private final AirCompanyRepository airCompanyRepository;

    @Override
    public Airplane save(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    @Override
    public Airplane get(Long id) {
        return airplaneRepository.getById(id);
    }

    @Override
    public List<Airplane> getAll() {
        return airplaneRepository.findAll();
    }

    @Override
    public Airplane updateAirCompany(Long airplaneId, Long airCompanyId) {
        Airplane airplane = airplaneRepository.getById(airplaneId);
        airplane.setAirCompany(airCompanyRepository.getById(airCompanyId));
        return airplaneRepository.save(airplane);
    }

    @Override
    public void delete(Long id) {
        airplaneRepository.deleteById(id);
    }
}
