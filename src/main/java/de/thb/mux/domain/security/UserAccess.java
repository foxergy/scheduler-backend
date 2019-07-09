package de.thb.mux.domain.security;

import de.thb.mux.domain.SurveyEvent;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UserAccess {

    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_access_generator")
    //@SequenceGenerator(name="user_access_generator", sequenceName = "user_access_seq", initialValue = 1, allocationSize = 1)
    //private Long id;

    @Column(unique = true)
    @Id
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Set<SurveyEvent> surveyEvents;

   // public Long getId() {
    //    return id;
    //}

    //public void setId(Long id) {
     //   this.id = id;
    //}

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<SurveyEvent> getSurveyEvents() {
        return surveyEvents;
    }

    public void setSurveyEvents(Set<SurveyEvent> surveyEvents) {
        this.surveyEvents = surveyEvents;
    }
}
