package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.enumeration.FuelState;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuelDTO {
    private Long id;
    private String name;
    private Double priceSell;
    private Double priceBuy;
}
