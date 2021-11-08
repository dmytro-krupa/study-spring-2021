package lpnu.mapper;

import lpnu.dto.FuelDTO;
import lpnu.dto.ManagerFuelDTO;
import lpnu.entity.Fuel;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {
    public ManagerFuelDTO toManagerDTO(final Fuel fuel){

        final ManagerFuelDTO managerFuelDTO = new ManagerFuelDTO();

        managerFuelDTO.setId(fuel.getId());
        managerFuelDTO.setName(fuel.getName());
        managerFuelDTO.setPriceBuy(fuel.getPriceBuy());
        managerFuelDTO.setPriceSell(fuel.getPriceSell());
        managerFuelDTO.setFuelState(fuel.getFuelState());

        return managerFuelDTO;
    }
}
