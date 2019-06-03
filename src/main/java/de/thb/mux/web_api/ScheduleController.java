package de.thb.mux.webApi;

import de.thb.mux.domain.Schedule;
import de.thb.mux.serviceApi.ScheduleApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@RestController
@RequestMapping(value = "/schedule")
@CrossOrigin(origins = "*")
public class ScheduleController {

    @Autowired
    private ScheduleApi scheduleApi;

    @GetMapping(value = "",
            produces = "application/json")
    public ResponseEntity<Collection<Schedule>> getAllSurveyEvents() {
        return new ResponseEntity<>(scheduleApi.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<Schedule> getSurveyEvent(@PathVariable("id") Long id) {
        return new ResponseEntity<>(scheduleApi.findByID(id), HttpStatus.OK);
    }

    @PostMapping(value = "",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity postSurveyEvents(@RequestBody Schedule schedule) {
        scheduleApi.create(schedule);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity putSurveyEvents(@PathVariable("id") Long id, @RequestBody Schedule schedule) {
        try {
            scheduleApi.update(schedule);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity((HttpStatus.NOT_FOUND));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteSurveyEvent(@PathVariable("id") Long id) {
        try {
            scheduleApi.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
