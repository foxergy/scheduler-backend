package de.thb.mux.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "schedule", "surveyEvent"})
public class SurveyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="survey_user_generator")
    @SequenceGenerator(name="survey_user_generator", sequenceName = "survey_user_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "surveyUser", cascade=CascadeType.ALL)
    private Set<de.thb.mux.domain.Schedule> schedule;

    @ManyToOne
    @JoinColumn(name = "survey_event_id")
    private SurveyEvent surveyEvent;

    public SurveyUser(Long id, String name) {
        this.name = name;
    }

    public SurveyUser(Long id, String name,SurveyEvent event) {
        this.name = name;
        this.surveyEvent = event;
    }

    public SurveyUser(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<de.thb.mux.domain.Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(Set<de.thb.mux.domain.Schedule> schedule) {
        this.schedule = schedule;
    }

    public SurveyEvent getSurveyEvent() {
        return surveyEvent;
    }

    public void setSurveyEvent(SurveyEvent surveyEvent) {
        this.surveyEvent = surveyEvent;
    }
}
