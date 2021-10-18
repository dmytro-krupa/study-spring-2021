package lpnu.resource;

import lpnu.entity.User;
import lpnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*

    Controller / RestController

    Service

    Repository

    Component

 */


@RestController
public class UserResource {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> getAllUsers(){



        final User user1 = new User(1,"name1", "surname1","email1");
        final User user2 = new User(2,"name2", "surname2","email2");

        return List.of(user1, user2);
    }

    @GetMapping("/users/{id}/{email}")
    public User getUserById(@PathVariable long id, @PathVariable String email){

        final User user1 = new User(1,"name1", "surname1","email1");
        final User user2 = new User(2,"name2", "surname2","email2");

        return List.of(user1, user2).stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }


    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        user.setId(10);

        return user;
    }
}

/*
Postman

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
