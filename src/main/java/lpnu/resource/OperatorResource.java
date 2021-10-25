package lpnu.resource;

import lpnu.dto.SimpleUserDTO;
import lpnu.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperatorResource {

    @Autowired
    private OperatorService operatorService;

    //todo  test it


    @GetMapping("/operators/users/{id}")
    public SimpleUserDTO getSimpleUserDTO(final @PathVariable long id){
        return operatorService.getSimpleUserById(id);
    }
}
