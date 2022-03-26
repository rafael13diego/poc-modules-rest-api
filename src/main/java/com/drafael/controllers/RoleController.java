package com.drafael.controllers;

import com.drafael.Services.RoleServices;
//import com.drafael.entities.Role;
import com.drafael.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleServices roleServices;

    @GetMapping
    public ResponseEntity<List<Role>> getRoles(){
        return new ResponseEntity<List<Role>>(roleServices.getRoles() , HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        return new ResponseEntity<Role>( roleServices.createRole(role), HttpStatus.CREATED);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable("roleId")Integer roleId, @RequestBody Role role){
        return new ResponseEntity<Role>( roleServices.updateRole(roleId, role), HttpStatus.OK);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable("roleId") Integer roleId){
        roleServices.deleteRole(roleId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }



}
