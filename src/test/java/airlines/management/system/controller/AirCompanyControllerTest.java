package airlines.management.system.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import airlines.management.system.dto.request.AirCompanyRequestDto;
import airlines.management.system.model.AirCompany;
import airlines.management.system.model.enums.AirCompanyType;
import airlines.management.system.service.AirCompanyService;
import airlines.management.system.util.DateTimePatternUtil;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class AirCompanyControllerTest {
    @MockBean
    private AirCompanyService airCompanyService;
    private static AirCompany wizzAir;
    private static AirCompany unitedAirlines;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void init() {
        wizzAir = new AirCompany();
        wizzAir.setId(1L);
        wizzAir.setName("WizzAir");
        wizzAir.setType(AirCompanyType.REGIONAL);
        wizzAir.setFoundedAt(LocalDate.of(2015, 10, 1));

        unitedAirlines = new AirCompany();
        unitedAirlines.setId(2L);
        unitedAirlines.setName("United Airlines");
        unitedAirlines.setType(AirCompanyType.NATIONAL);
        unitedAirlines.setFoundedAt(LocalDate.of(2008, 9, 2));
    }

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void shouldReturnAirCompanyById() {
        Mockito.when(airCompanyService.get(1L)).thenReturn(wizzAir);
        RestAssuredMockMvc
                .when()
                .get("/air-companies/1")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("name", Matchers.equalTo("WizzAir"));
    }

    @Test
    public void shouldReturnAllAirCompanies() {
        List<AirCompany> mockAirCompanies = List.of(wizzAir, unitedAirlines);
        Mockito.when(airCompanyService.getAll()).thenReturn(mockAirCompanies);
        RestAssuredMockMvc.when()
                .get("/air-companies")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(2))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].name", Matchers.equalTo("WizzAir"))
                .body("[1].id", Matchers.equalTo(2))
                .body("[1].name", Matchers.equalTo("United Airlines"));
    }

    @Test
    public void shouldAddAirCompany() {
        AirCompany airCompany = new AirCompany();
        airCompany.setName("Airways");
        airCompany.setType(AirCompanyType.MAJOR);
        airCompany.setFoundedAt(LocalDate.of(2008, 9, 2));

        AirCompany savedAirCompany = new AirCompany();
        savedAirCompany.setId(3L);
        savedAirCompany.setName("Airways");
        savedAirCompany.setType(AirCompanyType.MAJOR);
        savedAirCompany.setFoundedAt(LocalDate.of(2008, 9, 2));

        AirCompanyRequestDto requestDto = new AirCompanyRequestDto();
        requestDto.setName(airCompany.getName());
        requestDto.setType(airCompany.getType().toString());
        requestDto.setFoundedAt(airCompany.getFoundedAt()
                .format(DateTimeFormatter.ofPattern(DateTimePatternUtil.DATE_PATTERN)));

        Mockito.when(airCompanyService.save(airCompany)).thenReturn(savedAirCompany);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when()
                .post("/air-companies")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(3))
                .body("name", Matchers.equalTo("Airways"))
                .body("type", Matchers.equalTo("MAJOR"))
                .body("foundedAt", Matchers.equalTo("02.09.2008"));
    }
}
