package de.thb.mux.authentication.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

public class SurveyEvent {

    package de.thb.mux.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

    @Entity
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public class SurveyEvent {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="survey_event_generator")
        @SequenceGenerator(name="survey_event_generator", sequenceName = "survey_event_seq", initialValue = 1, allocationSize = 1)
        private Long id;

        private String name;
        private String description;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyEvent")
        private Set<Schedule> schedules;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyEvent")
        private Set<SurveyUser> user;

        public SurveyEvent(){}

        public SurveyEvent(Long id, String name, String description, Set<Schedule> schedules, Set<SurveyUser> surveyUsers) {
            this.name = name;
            this.description = description;
            this.schedules = schedules;
            this.user=surveyUsers;
        }

        public SurveyEvent(Long id, String name, String description, Set<Schedule> schedules) {
            this.name = name;
            this.description = description;
            this.schedules = schedules;
        }

        public SurveyEvent(Long id, String name, String description) {
            this.name = name;
            this.description = description;
        }

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Set<Schedule> getSchedules() {
            return schedules;
        }

        public void setSchedules(Set<Schedule> schedules) {
            this.schedules = schedules;
        }

        public Set<SurveyUser> getUser() {
            return user;
        }

        public void setUser(Set<SurveyUser> user) {
            this.user = user;
        }
    }

}
