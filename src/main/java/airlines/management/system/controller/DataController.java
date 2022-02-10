package airlines.management.system.controller;

import airlines.management.system.model.AirCompany;
import airlines.management.system.model.Airplane;
import airlines.management.system.model.Flight;
import airlines.management.system.model.enums.AirCompanyType;
import airlines.management.system.model.enums.AirplaneType;
import airlines.management.system.model.enums.FlightStatus;
import airlines.management.system.service.AirCompanyService;
import airlines.management.system.service.AirplaneService;
import airlines.management.system.service.FlightService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DataController {
    private final AirCompanyService airCompanyService;
    private final AirplaneService airplaneService;
    private final FlightService flightService;

    @PostMapping("/inject")
    public String injectData() {
        AirCompany wizzAir = new AirCompany();
        wizzAir.setName("WizzAir");
        wizzAir.setType(AirCompanyType.MAJOR);
        wizzAir.setFoundedAt(LocalDate.of(2015, 10, 1));
        airCompanyService.save(wizzAir);

        AirCompany unitedAirlines = new AirCompany();
        unitedAirlines.setName("United Airlines");
        unitedAirlines.setType(AirCompanyType.NATIONAL);
        unitedAirlines.setFoundedAt(LocalDate.of(2008, 9, 2));
        airCompanyService.save(unitedAirlines);

        AirCompany belavia = new AirCompany();
        belavia.setName("Belavia");
        belavia.setType(AirCompanyType.REGIONAL);
        belavia.setFoundedAt(LocalDate.of(2008, 9, 2));
        airCompanyService.save(belavia);

        Airplane airbus = new Airplane();
        airbus.setName("Airbus A330-200");
        airbus.setType(AirplaneType.PASSENGER);
        airbus.setAirCompany(unitedAirlines);
        airbus.setFuelCapacity(500L);
        airbus.setFlightDistance(2000L);
        airbus.setAmountFlights(23);
        airbus.setSerialNumber("A330-200");
        airbus.setCreatedAt(LocalDate.of(2021, 5, 1));
        airplaneService.save(airbus);

        Airplane boeing = new Airplane();
        boeing.setName("Boeing 737-700C");
        boeing.setType(AirplaneType.CARGO);
        boeing.setAirCompany(wizzAir);
        boeing.setFuelCapacity(700L);
        boeing.setFlightDistance(1000L);
        boeing.setAmountFlights(35);
        boeing.setSerialNumber("737-700C");
        boeing.setCreatedAt(LocalDate.of(2020, 1, 2));
        airplaneService.save(boeing);

        Airplane hughes = new Airplane();
        hughes.setName("Hughes H-4 Hercules");
        hughes.setType(AirplaneType.PASSENGER);
        hughes.setAirCompany(wizzAir);
        hughes.setFuelCapacity(700L);
        hughes.setFlightDistance(3000L);
        hughes.setAmountFlights(45);
        hughes.setSerialNumber("B1424");
        hughes.setCreatedAt(LocalDate.of(2015, 2, 2));
        airplaneService.save(hughes);

        Flight active = new Flight();
        active.setStatus(FlightStatus.ACTIVE);
        active.setAirCompany(wizzAir);
        active.setAirplanes(List.of(airbus));
        active.setDepartureCountry("Ukraine");
        active.setDestinationCountry("Poland");
        active.setDistance(1000);
        active.setEstimatedFlightTime(90);
        active.setStartedAt(LocalDateTime.of(2022, 2, 7, 8, 0));
        active.setEndedAt(LocalDateTime.of(2022, 2, 7, 9, 40));
        active.setCreatedAt(LocalDate.of(2015, 9, 7));
        flightService.save(active);

        Flight completed = new Flight();
        completed.setStatus(FlightStatus.COMPLETED);
        completed.setAirCompany(wizzAir);
        completed.setAirplanes(List.of(boeing, airbus));
        completed.setDepartureCountry("Ukraine");
        completed.setDestinationCountry("Germany");
        completed.setDistance(1400);
        completed.setEstimatedFlightTime(130);
        completed.setStartedAt(LocalDateTime.of(2022, 2, 6, 7, 0));
        completed.setEndedAt(LocalDateTime.of(2022, 2, 6, 9, 30));
        completed.setCreatedAt(LocalDate.of(2014, 10, 9));
        flightService.save(completed);

        Flight completedSecond = new Flight();
        completedSecond.setStatus(FlightStatus.COMPLETED);
        completedSecond.setAirCompany(wizzAir);
        completedSecond.setAirplanes(List.of(boeing));
        completedSecond.setDepartureCountry("Germany");
        completedSecond.setDestinationCountry("Ukraine");
        completedSecond.setDistance(1450);
        completedSecond.setEstimatedFlightTime(140);
        completedSecond.setStartedAt(LocalDateTime.of(2022, 2, 6, 8, 0));
        completedSecond.setEndedAt(LocalDateTime.of(2022, 2, 6, 10, 30));
        completedSecond.setCreatedAt(LocalDate.of(2013, 10, 9));
        flightService.save(completedSecond);
        return "Data added";
    }
}
