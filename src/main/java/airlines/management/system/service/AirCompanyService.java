package airlines.management.system.service;

import airlines.management.system.model.AirCompany;
import java.util.List;

public interface AirCompanyService {
    AirCompany save(AirCompany airCompany);

    AirCompany get(Long id);

    List<AirCompany> getAll();

    AirCompany update(Long id, AirCompany airCompany);

    void delete(Long id);
}
