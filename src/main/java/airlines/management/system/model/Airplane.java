package airlines.management.system.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import airlines.management.system.model.enums.AirplaneType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "airplanes")
public class Airplane {
    /*1) Airplane
    ID, name, factory serial number, air company id, number of flights, flight distance,
    fuel capacity, type, created at*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String serialNumber;
    @ManyToOne
    @JoinColumn(name = "air_company_id")
    private AirCompany airCompany;
    private Integer amountFlights;
    private Long flightDistance;
    private Long fuelCapacity;
    @Enumerated(EnumType.STRING)
    private AirplaneType type;
    private LocalDate createdAt;
}
