package de.thb.mux.web_api;

import de.thb.mux.domain.security.UserAccess;
import de.thb.mux.service.service_impl.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/user")

public class UserAccessController {

    @Autowired
    private UserDetailsImpl userDetailsService;

    @PostMapping(value = "/register",
            consumes = "application/json")
    ResponseEntity postSurveyUser(@RequestBody UserAccess userAccess){
        return new ResponseEntity<>( userDetailsService.createUserDetails(userAccess), HttpStatus.CREATED);
    }

}
