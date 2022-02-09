package airlines.management.system.service;

import java.util.List;
import airlines.management.system.model.Airplane;

public interface AirplaneService {
    Airplane save(Airplane airplane);

    Airplane get(Long id);

    List<Airplane> getAll();

    Airplane updateAirCompany(Long airplaneId, Long airCompanyId);

    void delete(Long id);
}
