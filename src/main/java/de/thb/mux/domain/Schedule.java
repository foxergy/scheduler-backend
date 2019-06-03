package de.thb.mux.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "surveyEvent"})
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="schedule_generator")
    @SequenceGenerator(name="schedule_generator", sequenceName = "schedule_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "survey_event_id")
    private SurveyEvent surveyEvent;

    @ManyToOne
    @JoinColumn(name = "survey_user_id")
    private SurveyUser surveyUser;

    public Schedule(){}

    public Schedule(Long id, LocalDateTime time, SurveyEvent surveyEvent, SurveyUser surveyUser) {
        this.time = time;
        this.surveyEvent = surveyEvent;
        this.surveyUser = surveyUser;
    }

    public Schedule(Long id, LocalDateTime time, SurveyEvent surveyEvent) {
        this.time = time;
        this.surveyEvent = surveyEvent;
    }

    public Schedule(Long id, LocalDateTime time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public SurveyEvent getSurveyEvent() {
        return surveyEvent;
    }

    public void setSurveyEvent(SurveyEvent surveyEvent) {
        this.surveyEvent = surveyEvent;
    }

    public SurveyUser getsurveyUser() {
        return surveyUser;
    }

    public void setSurveyUser(SurveyUser surveyUser) {
        this.surveyUser = surveyUser;
    }
}
