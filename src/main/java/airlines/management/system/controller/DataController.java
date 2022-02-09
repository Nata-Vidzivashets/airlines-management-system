package airlines.management.system.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import airlines.management.system.model.AirCompany;
import airlines.management.system.model.Airplane;
import airlines.management.system.model.Flight;
import airlines.management.system.model.enums.AirCompanyType;
import airlines.management.system.model.enums.AirplaneType;
import airlines.management.system.model.enums.FlightStatus;
import airlines.management.system.service.AirCompanyService;
import airlines.management.system.service.AirplaneService;
import airlines.management.system.service.FlightService;
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
        AirCompany national = new AirCompany();
        national.setName("National");
        national.setType(AirCompanyType.NATIONAL);
        national.setFoundedAt(LocalDate.now().minusYears(5));
        airCompanyService.save(national);

        AirCompany major = new AirCompany();
        major.setName("Major");
        major.setType(AirCompanyType.MAJOR);
        major.setFoundedAt(LocalDate.now().minusYears(10));
        airCompanyService.save(major);

        Airplane passenger = new Airplane();
        passenger.setName("Passenger");
        passenger.setType(AirplaneType.PASSENGER);
        passenger.setAirCompany(national);
        passenger.setFuelCapacity(500L);
        passenger.setFlightDistance(20000L);
        passenger.setAmountFlights(23);
        passenger.setSerialNumber("N1234");
        passenger.setCreatedAt(LocalDate.now().minusYears(2));
        airplaneService.save(passenger);

        Airplane cargo = new Airplane();
        cargo.setName("Cargo");
        cargo.setType(AirplaneType.CARGO);
        cargo.setAirCompany(major);
        cargo.setFuelCapacity(200L);
        cargo.setFlightDistance(10000L);
        cargo.setAmountFlights(15);
        cargo.setSerialNumber("C1234");
        cargo.setCreatedAt(LocalDate.now().minusYears(4));
        airplaneService.save(cargo);

        Flight active = new Flight();
        active.setStatus(FlightStatus.ACTIVE);
        active.setAirCompany(national);
        active.setAirplanes(List.of(cargo));
        active.setDepartureCountry("USA");
        active.setDestinationCountry("UA");
        active.setDistance(1000);
        active.setEstimatedFlightTime(90);
        active.setStartedAt(LocalDateTime.now().minusDays(1L).minusHours(2L).withSecond(0).withNano(0));
        active.setEndedAt(LocalDateTime.now().minusHours(1L).withNano(0));
        active.setDelayStartedAt(LocalDateTime.now().minusHours(4L).withNano(0));
        active.setCreatedAt(LocalDate.now().minusYears(10L));
        flightService.save(active);

        Flight completed = new Flight();
        completed.setStatus(FlightStatus.COMPLETED);
        completed.setAirCompany(national);
        completed.setAirplanes(List.of(cargo, passenger));
        completed.setDepartureCountry("China");
        completed.setDestinationCountry("Polish");
        completed.setDistance(900);
        completed.setEstimatedFlightTime(60);
        completed.setStartedAt(LocalDateTime.now().minusDays(1L).minusHours(2L).withSecond(0).withNano(0));
        completed.setEndedAt(LocalDateTime.now().minusHours(1L).withNano(0));
        completed.setDelayStartedAt(LocalDateTime.now().minusHours(4L).withNano(0));
        completed.setCreatedAt(LocalDate.now().minusYears(10L));
        flightService.save(completed);

        Flight active1 = new Flight();
        active1.setStatus(FlightStatus.ACTIVE);
        active1.setAirCompany(national);
        active1.setAirplanes(List.of(passenger));
        active1.setDepartureCountry("UA");
        active1.setDestinationCountry("Polish");
        active1.setDistance(800);
        active1.setEstimatedFlightTime(50);
        active1.setStartedAt(LocalDateTime.now().minusDays(1L).minusHours(2L).withSecond(0).withNano(0));
        active1.setEndedAt(LocalDateTime.now().minusHours(1L).withNano(0));
        active1.setDelayStartedAt(LocalDateTime.now().minusHours(4L).withNano(0));
        active1.setCreatedAt(LocalDate.now().minusYears(10L));
        flightService.save(active1);
        return "done";
    }
}
