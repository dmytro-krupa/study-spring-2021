package lpnu.resource;

import lpnu.dto.FuelDTO;
import lpnu.dto.ManagerFuelDTO;
import lpnu.service.FuelService;
import lpnu.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ManagerResource {
    @Autowired
    private FuelService fuelService;

    @Autowired
    private ManagerService managerService;

    @GetMapping("/manager/fuel")
    public List<ManagerFuelDTO> getAllFuel() {
        return fuelService.getAllFuelsForManager();
    }

    @GetMapping("/manager/approve/{fuelId}")
    public ResponseEntity approveFuelById(final @PathVariable Long fuelId) {
        managerService.approve(fuelId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/manager/deactivate/{fuelId}")
    public ResponseEntity deactivateFuel(final @PathVariable Long fuelId) {
        managerService.deactivate(fuelId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
