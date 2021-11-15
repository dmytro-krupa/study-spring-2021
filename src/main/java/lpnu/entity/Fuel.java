package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.enumeration.FuelState;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fuel {
    private Long id;
    //todo fix name
    private String name;
    private Double priceSell;
    private Double priceBuy;
    private FuelState fuelState;
}
