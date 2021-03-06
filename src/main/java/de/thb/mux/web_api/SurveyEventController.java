package de.thb.mux.web_api;

import de.thb.mux.domain.SurveyEvent;
import de.thb.mux.domain.security.UserAccess;
import de.thb.mux.service.service_api.SurveyEventApi;
import de.thb.mux.service.service_impl.UUIDService;
import de.thb.mux.service.service_impl.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/surveyEvent")
public class SurveyEventController {

    @Autowired
    private SurveyEventApi surveyEventApi;
    @Autowired
    private UUIDService uuidService;
    @Autowired
    private UserDetailsImpl userDetailsService;

    @GetMapping(
            produces = "application/json")
     public ResponseEntity<Collection<SurveyEvent>> getAllSurveyEvents(){
        return new ResponseEntity<>(surveyEventApi.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/{id}",
            produces = "application/json")
    public ResponseEntity<SurveyEvent> getSurveyEvent(@PathVariable("id")String id) {
        return new ResponseEntity<>(surveyEventApi.findByID(id), HttpStatus.OK);
    }

    @GetMapping(value="/user/{username}",
            produces = "application/json")
    public ResponseEntity<Collection<SurveyEvent>> getSurveyEventByUserID(@PathVariable("username")String username) {
        UserAccess user = userDetailsService.loadUserAccessByUsername(username);
        return new ResponseEntity<>(surveyEventApi.findSurveyEventByUsername(user), HttpStatus.OK);
    }

    @PostMapping(value = "/user/{username}",
            consumes = "application/json",
            produces = "text/plain")
    public ResponseEntity<String> postSurveyEvents(@RequestBody SurveyEvent surveyEvent, @PathVariable("username")String username){
        UserAccess user = userDetailsService.loadUserAccessByUsername(username);
        surveyEvent.setId(uuidService.generateUUID());
        surveyEvent.getSchedules().forEach(schedule -> schedule.setSurveyEvent(surveyEvent));
        surveyEvent.setUsername(user);
        surveyEventApi.create(surveyEvent);
            return new ResponseEntity<>(surveyEvent.getId(), HttpStatus.CREATED);
    }

    @PutMapping(value= "",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity putSurveyEvents(@RequestBody SurveyEvent surveyEvent){
        try {
            surveyEvent.getUser().forEach(user -> {
                user.setSurveyEvent(surveyEvent);
            });
            surveyEvent.getSchedules().forEach(schedule -> {
                schedule.setSurveyEvent(surveyEvent);
            });
            surveyEventApi.update(surveyEvent);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (EntityNotFoundException e){
            return  new ResponseEntity((HttpStatus.NOT_FOUND));
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteSurveyEvent(@PathVariable("id")String id){
        try{
            surveyEventApi.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
