package airlines.management.system.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AirCompanyResponseDto {
    private Long id;
    private String name;
    private String type;
    private String foundedAt;
}
