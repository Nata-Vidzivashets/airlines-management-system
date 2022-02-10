package airlines.management.system.service.impl;

import airlines.management.system.model.AirCompany;
import airlines.management.system.repository.AirCompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AirCompanyServiceImplTest {
    @InjectMocks
    private AirCompanyServiceImpl airCompanyService;

    @Mock
    private AirCompanyRepository airCompanyRepository;

    @Test
    void shouldUpdateAirCompany() {
        AirCompany wizzAir = new AirCompany();
        wizzAir.setId(1L);
        wizzAir.setName("WizzAir");

        Mockito.when(airCompanyRepository.save(wizzAir)).thenReturn(wizzAir);
        AirCompany actual = airCompanyService.update(1L, wizzAir);
        Assertions.assertEquals(wizzAir.getId(), actual.getId());
        Assertions.assertEquals(wizzAir.getName(), actual.getName());
    }
}
