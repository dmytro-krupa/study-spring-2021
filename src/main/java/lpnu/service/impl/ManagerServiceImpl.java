package lpnu.service.impl;

import lpnu.entity.Fuel;
import lpnu.entity.enumeration.FuelState;
import lpnu.repository.FuelRepository;
import lpnu.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private FuelRepository fuelRepository;

    @Override
    public void approve(final Long fuelId) {
        final Fuel fuel = fuelRepository.getFuelById(fuelId);

        fuel.setFuelState(FuelState.ACTIVE);

        fuelRepository.updateFuel(fuel);
    }
}
