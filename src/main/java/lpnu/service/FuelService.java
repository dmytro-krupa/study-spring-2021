package lpnu.service;

import lpnu.dto.FuelDTO;
import lpnu.dto.ManagerFuelDTO;

import java.util.List;

public interface FuelService {

    List<FuelDTO> getAllFuels();
    FuelDTO getFuelById(long id);
    FuelDTO saveFuel(FuelDTO fuelDTO);
    FuelDTO updateFuel(FuelDTO fuelDTO);
    void deleteFuelById(long id);

    List<ManagerFuelDTO> getAllFuelsForManager();
    
}
