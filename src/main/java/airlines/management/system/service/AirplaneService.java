package airlines.management.system.service;

import airlines.management.system.model.Airplane;
import java.util.List;

public interface AirplaneService {
    Airplane save(Airplane airplane);

    Airplane get(Long id);

    List<Airplane> getAll();

    Airplane updateAirCompany(Long airplaneId, Long airCompanyId);

    void delete(Long id);
}
