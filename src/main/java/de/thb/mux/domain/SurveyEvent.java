package de.thb.mux.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

    @Entity
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public class SurveyEvent {

        @Id
        private String id;

        private String name;
        private String description;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyEvent")
        private Set<Schedule> schedules;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyEvent")
        private Set<SurveyUser> user;

        public SurveyEvent(){}

        public SurveyEvent(String id, String name, String description, Set<Schedule> schedules, Set<SurveyUser> surveyUsers) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.schedules = schedules;
            this.user=surveyUsers;
        }

        public SurveyEvent(String id, String name, String description, Set<Schedule> schedules) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.schedules = schedules;
        }

        public SurveyEvent(String id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
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
