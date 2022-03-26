package com.drafael.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Steryotype
@RequestMapping("/hello")
public class DiegoController {

    @GetMapping
    public String helloWorld(){
        return "Hello WOrld & Diego";
    }

}
