package lpnu.service.impl;

import lpnu.dto.FuelDTO;
import lpnu.dto.ManagerFuelDTO;
import lpnu.entity.Fuel;
import lpnu.entity.enumeration.FuelState;
import lpnu.mapper.FuelToFuelDTOMapper;
import lpnu.mapper.ManagerMapper;
import lpnu.repository.FuelRepository;
import lpnu.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelServiceImpl implements FuelService {
    @Autowired
    private FuelRepository fuelRepository;

    @Autowired
    private FuelToFuelDTOMapper fuelMapper;

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<FuelDTO> getAllFuels() {
        return fuelRepository.getAllFuel().stream().map(fuelMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ManagerFuelDTO> getAllFuelsForManager() {
        return fuelRepository.getAllFuelForManager().stream().map(managerMapper::toManagerDTO).collect(Collectors.toList());
    }


    @Override
    public FuelDTO getFuelById(final long id) {
        return fuelMapper.toDTO(fuelRepository.getFuelById(id));
    }

    @Override
    public FuelDTO saveFuel(final FuelDTO fuelDTO) {
        final Fuel fuel = fuelMapper.toEntity(fuelDTO);

        fuel.setFuelState(FuelState.NOT_APPROVED);

        return fuelMapper.toDTO(fuelRepository.saveFuel(fuel));
    }

    @Override
    public FuelDTO updateFuel(final FuelDTO fuelDTO) {
        final Fuel fuel = fuelMapper.toEntity(fuelDTO);

        fuel.setFuelState(FuelState.NOT_APPROVED);

        return fuelMapper.toDTO(fuelRepository.updateFuel(fuel));
    }

    @Override
    public void deleteFuelById(final long id) {
        final Fuel savedFuel = fuelRepository.getFuelById(id);
        savedFuel.setFuelState(FuelState.DELETED);
        fuelRepository.saveFuel(savedFuel);
    }
}
