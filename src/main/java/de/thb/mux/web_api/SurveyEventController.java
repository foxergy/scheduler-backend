package de.thb.mux.webApi;

import de.thb.mux.domain.SurveyEvent;
import de.thb.mux.serviceApi.SurveyEventApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@RestController
@RequestMapping(value = "/surveyEvent")
@CrossOrigin(origins = "*")
public class SurveyEventController {

    @Autowired
    private SurveyEventApi surveyEventApi;

    @GetMapping(
            produces = "application/json")
    public ResponseEntity<Collection<SurveyEvent>> getAllSurveyEvents(){
        return new ResponseEntity<>(surveyEventApi.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/{id}",
            produces = "application/json")
    public ResponseEntity<SurveyEvent> getSurveyEvent(@PathVariable("id")Long id) {
        return new ResponseEntity<>(surveyEventApi.findByID(id), HttpStatus.OK);
    }

    @PostMapping(value = "",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity postSurveyEvents(@RequestBody SurveyEvent surveyEvent){
            surveyEvent.getSchedules().forEach(schedule -> schedule.setSurveyEvent(surveyEvent));
            surveyEventApi.create(surveyEvent);
            return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value= "",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity putSurveyEvents( @RequestBody SurveyEvent surveyEvent){
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
    public ResponseEntity deleteSurveyEvent(@PathVariable("id")Long id){
        try{
            surveyEventApi.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
