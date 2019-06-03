package de.thb.mux.web_api;

import de.thb.mux.domain.SurveyUser;
import de.thb.mux.service.service_api.SurveyUserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/surveyUser")
@CrossOrigin(origins = "*")
public class SurveyUserController {

    @Autowired
    SurveyUserApi surveyUserApi;

    @PostMapping(value = "",
    consumes = "application/json")
    ResponseEntity postSurveyUser(@RequestBody SurveyUser surveyUser){
        surveyUserApi.create(surveyUser);
    return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(
            produces = "application/json")
    public ResponseEntity<Collection<SurveyUser>> getAllSurveyEvents(){
        return new ResponseEntity<>(surveyUserApi.findAll(), HttpStatus.OK);
    }

}
