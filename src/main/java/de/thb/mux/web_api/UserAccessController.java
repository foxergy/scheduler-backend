package de.thb.mux.web_api;

import de.thb.mux.domain.security.UserAccess;
import de.thb.mux.service.service_impl.security.UserDetailsImpl;
import org.hibernate.NonUniqueResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameAlreadyBoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/user")
public class UserAccessController {

    Logger log = LoggerFactory.getLogger(UserAccessController.class);
    @Autowired
    private UserDetailsImpl userDetailsService;

    @PostMapping(value = "/register",
            consumes = "application/json",
            produces = "application/json")
    ResponseEntity<?> postSurveyUser(@RequestBody UserAccess userAccess){
        try {
            return new ResponseEntity<>(userDetailsService.createUserDetails(userAccess), HttpStatus.CREATED);
        } catch (NameAlreadyBoundException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/login",
            consumes = "application/json")
    ResponseEntity<String> loginUser(){
        ResponseEntity response = new ResponseEntity<>("200", HttpStatus.OK);
        return response;
    }
}
