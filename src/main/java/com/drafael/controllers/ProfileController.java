package com.drafael.controllers;

import com.drafael.Services.ProfileServices;
import com.drafael.entities.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/profiles")
//@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileServices profileServices;


    @GetMapping
    public ResponseEntity<List<Profile>> getProfiles() {
        return new ResponseEntity<List<Profile>>(profileServices.getProfiles(), HttpStatus.OK);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getById(@PathVariable("userId") Integer userId, @PathVariable("profileId") Integer profileId) {
        return new ResponseEntity<Profile>(profileServices.getByUserAndProfileId(userId, profileId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Profile> create(@PathVariable("userId") Integer userId, @RequestBody Profile profile) {
        return new ResponseEntity<Profile>(profileServices.create(userId, profile), HttpStatus.CREATED);
    }


}
