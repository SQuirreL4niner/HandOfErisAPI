package com.handoferis.handoferis.controllers;


import com.handoferis.handoferis.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private Environment environment;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/users")
    public String createUser(){
        return "hello";
        //User user = service.save(user);
    }

    @GetMapping("/users-private")
    public void protectedUserData(){
        //stuff
    }
}
