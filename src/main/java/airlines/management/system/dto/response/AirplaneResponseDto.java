package airlines.management.system.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirplaneResponseDto {
    private Long id;
    private String name;
    private String serialNumber;
    private Long airCompanyId;
    private Integer amountFlights;
    private Long flightDistance;
    private Long fuelCapacity;
    private String type;
    private String createdAt;
}
