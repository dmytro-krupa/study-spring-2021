package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuelDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    private Double priceSell;
    @NotNull
    @Min(1)
    private Double priceBuy;
}
