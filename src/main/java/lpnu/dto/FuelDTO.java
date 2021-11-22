package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.validation.CrossCheck;
import lpnu.validation.enumeration.Condition;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

@CrossCheck(field1 = "priceSell", condition = Condition.GREATER_THAN, field2 = "priceBuy", message = "We can't sell fuel for this money. We need to make a profit!!!")
public class FuelDTO {
    private Long id;

    @NotBlank
    private String name;

    @Min(1)
    @NotNull
    private Double priceSell;

    @Min(1)
    @NotNull
    private Double priceBuy;
}
