package airlines.management.system.service;

import java.util.List;
import airlines.management.system.model.AirCompany;

public interface AirCompanyService {
    AirCompany save(AirCompany airCompany);

    AirCompany get(Long id);

    List<AirCompany> getAll();

    AirCompany update(Long id, AirCompany airCompany);

    void delete(Long id);
}
