package lpnu.resource;

import lpnu.dto.FuelDTO;
import lpnu.dto.ManagerFuelDTO;
import lpnu.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ManagerResource {
    @Autowired
    private FuelService fuelService;

    @GetMapping("/manager/fuel")
    public List<ManagerFuelDTO> getAllFuel() {
        return fuelService.getAllFuelsForManager();
    }

}
