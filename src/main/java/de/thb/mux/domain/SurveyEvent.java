package de.thb.mux.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.thb.mux.domain.security.UserAccess;

import javax.persistence.*;
import java.util.Set;

    @Entity
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "username"})
    public class SurveyEvent {

        @Id
        private String id;

        private String name;
        private String description;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyEvent")
        private Set<Schedule> schedules;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyEvent")
        private Set<SurveyUser> user;

        @ManyToOne
        @JoinColumn(name = "username")
        private UserAccess username;

        public SurveyEvent(){}

        public SurveyEvent(String id, String name, String description, Set<Schedule> schedules, Set<SurveyUser> surveyUsers, UserAccess username) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.schedules = schedules;
            this.user=surveyUsers;
            this.username = username;
        }

        public SurveyEvent(String id, String name, String description, Set<Schedule> schedules, UserAccess username) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.schedules = schedules;
            this.username=username;
        }

        public SurveyEvent(String id, String name, String description, UserAccess username) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.username=username;
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

        public UserAccess getUsername() {
            return username;
        }

        public void setUsername(UserAccess username) {
            this.username = username;
        }
    }
