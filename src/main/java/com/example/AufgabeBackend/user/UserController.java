package com.example.AufgabeBackend.user;

import com.example.AufgabeBackend.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@RequestBody User newUser){
        userService.createUser(newUser);
    }

}
