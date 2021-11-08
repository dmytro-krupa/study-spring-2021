package lpnu.mapper;

import lpnu.dto.FuelDTO;
import lpnu.entity.Fuel;
import org.springframework.stereotype.Component;

@Component
public class FuelToFuelDTOMapper {
    public Fuel toEntity(final FuelDTO fuelDTO){
        final Fuel fuel = new Fuel();

        fuel.setId(fuelDTO.getId());
        fuel.setName(fuelDTO.getName());
        fuel.setPriceSell(fuelDTO.getPriceSell());
        fuel.setPriceBuy(fuelDTO.getPriceBuy());

        return fuel;
    }

    public FuelDTO toDTO(final Fuel fuel){
        final FuelDTO fuelDTO = new FuelDTO();

        fuelDTO.setId(fuel.getId());
        fuelDTO.setName(fuel.getName());
        fuelDTO.setPriceBuy(fuel.getPriceBuy());
        fuelDTO.setPriceSell(fuel.getPriceSell());

        return fuelDTO;
    }
}
