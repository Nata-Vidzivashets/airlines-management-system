package airlines.management.system.service.impl;

import airlines.management.system.model.AirCompany;
import airlines.management.system.repository.AirCompanyRepository;
import airlines.management.system.service.AirCompanyService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AirCompanyServiceImpl implements AirCompanyService {
    private final AirCompanyRepository airCompanyRepository;

    @Override
    public AirCompany save(AirCompany airCompany) {
        return airCompanyRepository.save(airCompany);
    }

    @Override
    public AirCompany get(Long id) {
        return airCompanyRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can`t get air company with id: " + id));
    }

    @Override
    public List<AirCompany> getAll() {
        return airCompanyRepository.findAll();
    }

    @Override
    public AirCompany update(Long id, AirCompany airCompany) {
        airCompany.setId(id);
        return airCompanyRepository.save(airCompany);
    }

    @Override
    public void delete(Long id) {
        airCompanyRepository.deleteById(id);
    }
}
