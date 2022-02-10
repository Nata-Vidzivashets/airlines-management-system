package airlines.management.system.service.impl;

import airlines.management.system.model.AirCompany;
import airlines.management.system.model.Airplane;
import airlines.management.system.repository.AirCompanyRepository;
import airlines.management.system.repository.AirplaneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AirplaneServiceImplTest {
    @InjectMocks
    private AirplaneServiceImpl airplaneService;

    @Mock
    private AirplaneRepository airplaneRepository;
    @Mock
    private AirCompanyRepository airCompanyRepository;

    @Test
    void shouldUpdateAirCompany() {
        AirCompany wizzAir = new AirCompany();
        wizzAir.setId(1L);
        wizzAir.setName("WizzAir");

        Airplane boeing = new Airplane();
        boeing.setId(1L);
        boeing.setName("Boeing 377");
        boeing.setAirCompany(wizzAir);

        AirCompany actualAirCompany = new AirCompany();
        actualAirCompany.setId(2L);
        actualAirCompany.setName("United Airlines");

        Mockito.when(airplaneRepository.getById(1L)).thenReturn(boeing);
        Mockito.when(airCompanyRepository.getById(2L)).thenReturn(actualAirCompany);
        Mockito.when(airplaneRepository.save(boeing)).thenReturn(boeing);
        Airplane actualAirplane = airplaneService.updateAirCompany(1L, 2L);
        Assertions.assertEquals(actualAirCompany.getName(), actualAirplane.getAirCompany().getName());
    }
}
