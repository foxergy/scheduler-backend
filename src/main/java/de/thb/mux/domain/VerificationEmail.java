package de.thb.mux.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VerificationEmail {

    @Id
    private String email;

    private String name;
    private Integer verificationCode;

    public Integer getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
