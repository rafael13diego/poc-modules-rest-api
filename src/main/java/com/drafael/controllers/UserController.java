package com.drafael.controllers;

import com.drafael.Services.UserInRoleServices;
import com.drafael.Services.UserServices;
import com.drafael.entities.User;
import com.drafael.entities.UserInRole;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Autowired
    private UserInRoleServices userInRoleServices;

    @GetMapping
    @Timed("get.users") //métricas monitoreo
    public ResponseEntity<Page<User>> getUsers(@RequestParam(required = false, defaultValue = "0", value = "page") int page, @RequestParam(required = false, defaultValue = "10", value = "size") int size) {
        return new ResponseEntity<Page<User>>(userServices.getUsers(page, size), HttpStatus.OK);
    }
    /*@GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<List<User>>(userServices.getUsers(page, size), HttpStatus.OK);
    }*/

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserByiD(@PathVariable("userId") Integer userId) {
        return new ResponseEntity<User>(userServices.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<User>(userServices.getUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> authenticate(@RequestBody User user) {
        return new ResponseEntity<User>(userServices.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()),
                HttpStatus.OK);
    }

    @DeleteMapping("/username/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable("username") String username) {
        userServices.deleteUserByUsername(username);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/byrole/{roleId}")
    public ResponseEntity<List<UserInRole>> getUsersByRoleId(@PathVariable("roleId") Integer roleId){

        return new ResponseEntity<List<UserInRole>>( userInRoleServices.getUsersByRoleId(roleId), HttpStatus.OK);
    }


}
