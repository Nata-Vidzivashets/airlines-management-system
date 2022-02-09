package airlines.management.system.dto.request;

import java.util.List;
import airlines.management.system.model.enums.FlightStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightRequestDto {
    private String status;
    private Long airCompanyId;
    private List<Long> airplanesId;
    private String departureCountry;
    private String destinationCountry;
    private Integer distance;
    private Integer estimatedFlightTime;
    private String startedAt;
    private String endedAt;
    private String delayStartedAt;
    private String createdAt;
}
