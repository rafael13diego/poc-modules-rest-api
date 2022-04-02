package com.drafael.controllers;

import com.drafael.Services.AddressServices;
import com.drafael.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/profiles/{profileId}/addresses")
public class AddressController {


    @Autowired
    private AddressServices addressServices;

    @GetMapping
    public ResponseEntity<List<Address>> findAddressByProfileIdAndUser(@PathVariable("userId") Integer userId,
                                                                       @PathVariable("profileId") Integer profileId) {
        return new ResponseEntity<List<Address>>(addressServices.findAddressByProfileIdAndUser(userId, profileId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Address> create(@PathVariable("userId") Integer userId,
                                          @PathVariable("profileId") Integer profileId,
                                          @RequestBody Address address) {
        return new ResponseEntity<Address>(addressServices.createAddress(userId, profileId, address), HttpStatus.CREATED);
    }
}
