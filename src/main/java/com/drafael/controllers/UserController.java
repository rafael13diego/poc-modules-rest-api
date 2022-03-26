package com.drafael.controllers;

import com.drafael.Services.UserServices;
import com.drafael.entities.User;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    @Timed("get.users")
    public ResponseEntity<Page<User>> getUsers(@RequestParam(required = false, defaultValue = "0", value="page") int page,@RequestParam(required = false, defaultValue = "10",value="size") int size){
        return new ResponseEntity<Page<User>>(userServices.getUsers(page, size), HttpStatus.OK);
    }
    /*@GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<List<User>>(userServices.getUsers(page, size), HttpStatus.OK);
    }*/

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserByiD(@PathVariable("userId") Integer userId){
        return new ResponseEntity<User>(userServices.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
        return new ResponseEntity<User>(userServices.getUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> authenticate(@RequestBody User user){
        return new ResponseEntity<User>(userServices.getUserByUsernameAndPassword(user.getUsername(),user.getPassword()),
                HttpStatus.OK);
    }



}
