package airlines.management.system.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AirCompanyRequestDto {
    private String name;
    private String type;
    private String foundedAt;
}
