package com.drafael.controllers;

import com.drafael.Services.UserServicesUsingList;
import com.drafael.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserControllerUsingList {

    @Autowired
    private UserServicesUsingList userService;

    @GetMapping
    //Método HTTP - Recurso -> Handlers Methods
    public ResponseEntity<List<User>> getUsers(@RequestParam(value= "startWith", required = false) String startWith){
        return new ResponseEntity<List<User>>(
                userService.getUsers(startWith), HttpStatus.OK);
    }
    /*public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<List<User>>(
                userService.getUsers(), HttpStatus.OK);
    }*/

    @GetMapping(value = "/{username}")
    public ResponseEntity<User> getUserByName(@PathVariable("username") String username){
        return new ResponseEntity<User>(
                userService.getUserByUserName(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

        return new ResponseEntity<User>( userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("username") String username){
        return new ResponseEntity<User>( userService.updateUser(user, username), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username){
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
