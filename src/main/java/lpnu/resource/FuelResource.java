package lpnu.resource;

import lpnu.dto.FuelDTO;
import lpnu.dto.UserDTO;
import lpnu.service.FuelService;
import lpnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FuelResource {
    @Autowired
    private FuelService fuelService;

    @GetMapping("/fuel")
    public List<FuelDTO> getAllFuel() {
        return fuelService.getAllFuels();
    }

    @GetMapping("/fuel/{id}")
    public FuelDTO getFuelById(@PathVariable final long id) {
        return fuelService.getFuelById(id);
    }

    @PostMapping("/fuel")
    public FuelDTO saveFuel(@RequestBody @Validated final FuelDTO fuelDTO) {
        return fuelService.saveFuel(fuelDTO);
    }

    @PutMapping("/fuel")
    public FuelDTO updateFuel(@RequestBody @Validated final FuelDTO fuelDTO) {
        return fuelService.updateFuel(fuelDTO);
    }

    @DeleteMapping("/fuel/{id}")
    public ResponseEntity deleteFuelById(@PathVariable final long id) {
        fuelService.deleteFuelById(id);
        return ResponseEntity.ok().build();
    }
}
