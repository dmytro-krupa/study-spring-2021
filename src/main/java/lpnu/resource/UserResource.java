package lpnu.resource;

import lpnu.dto.UserDTO;
import lpnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserResource {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable final long id) {
        return userService.getUserById(id);
    }


    @PostMapping("/users")
    public UserDTO saveUser(@RequestBody final UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }


    @PutMapping("/users")
    public UserDTO updateUser(@RequestBody final UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUserById(@PathVariable final long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}







/*
Postman

    500 - проблема сервера, погано написаний код. Наприклад впав NullPointer

    200 - OK
    201 - created - об'єкт був створений


    400 - Bad request   - юзер прислав погані дані
    401 - Unauthorized  - не залогінилися але хоче щось зробити
    403 - Forbidden     - не залогінилися але хоче щось зробити. Але навіть якщо залогінитеся то не маєте права нічого робити
    404 - page not found / resource not found


    GET - отримати один або багато ресурсів

    POST - зберегти один ресурс

    PUT - оновлює повністю один ресурс

    PATCH - оновлює частково один ресурс

    DELETE - видалити один або багато ресурсів



    {
        "id" : 8
        "name" : "N",
        "surname" : "S",
        "email" : "E@E"
    }
     + by PUT
    {
        "id" : 8
        "name" : "123",
        "surname" : "456"
    }

    =
     {
        "id" : 8
        "name" : "123",
        "surname" : "456",
        "email" : null
    }

    ==========================================

       {
        "id" : 8
        "name" : "N",
        "surname" : "S",
        "email" : "E@E"
    }
     + by PATCH
    {
        "id" : 8
        "name" : "123",
        "surname" : "456"
    }

    =
     {
        "id" : 8
        "name" : "123",
        "surname" : "456",
        "email" : "E@E"
    }

 */
