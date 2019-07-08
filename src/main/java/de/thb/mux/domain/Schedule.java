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

    private LocalDateTime startTime;

    private LocalDateTime endTime;


    @ManyToOne
    @JoinColumn(name = "survey_event_id")
    private SurveyEvent surveyEvent;

    @ManyToOne
    @JoinColumn(name = "survey_user_id")
    private SurveyUser surveyUser;

    public Schedule(){}

    public Schedule(Long id, LocalDateTime startTime, LocalDateTime endTime, SurveyEvent surveyEvent, SurveyUser surveyUser) {
        this.endTime = endTime;
        this.startTime = startTime;
        this.surveyEvent = surveyEvent;
        this.surveyUser = surveyUser;
    }

    public Schedule(Long id, LocalDateTime startTime, LocalDateTime endTime, SurveyEvent surveyEvent) {
        this.endTime = endTime;
        this.startTime = startTime;
        this.surveyEvent = surveyEvent;
    }

    public Schedule(Long id, LocalDateTime startTime, LocalDateTime endTime) {
        this.endTime = endTime;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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
