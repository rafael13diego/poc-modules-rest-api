package com.drafael.controllers;

import com.drafael.Services.UserInRoleServices;
import com.drafael.entities.UserInRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userinrole")
public class UserInRoleController {
    //asignación de un rol a un usuario
    // Roles disponibles
    //Usuarios con rol de admin etc

    @Autowired
    private UserInRoleServices userInRoleServices;


    @PostMapping("/users/{userId}/roles/{roleId}")
    public ResponseEntity<UserInRole> assingRole(@PathVariable("userId") Integer userId,
                                                 @PathVariable("roleId") Integer roleId) {

        return new ResponseEntity<UserInRole>(userInRoleServices.assingRole(userId, roleId), HttpStatus.CREATED);
    }


}
