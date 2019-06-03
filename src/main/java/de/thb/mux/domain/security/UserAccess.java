package de.thb.mux.domain.security;

import javax.persistence.*;

@Entity
public class UserAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_access_generator")
    @SequenceGenerator(name="user_access_generator", sequenceName = "user_access_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
