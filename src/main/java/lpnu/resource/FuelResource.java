package lpnu.resource;

import lpnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FuelResource {
    @Autowired
    private UserService userService;
}
