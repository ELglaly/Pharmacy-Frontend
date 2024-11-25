package org.example.pharmacymanagmentfrontend.Model;

import java.util.Date;
import java.util.List;

public class UserLogs {
    private Date loginTime; // Tracks successful login timestamps
    private Boolean successfulLogin;
    private Person person;
    private String username;

    public UserLogs(Date loginTime, Boolean successfulLogin, Person person,String username) {
        this.loginTime = loginTime;
        this.successfulLogin = successfulLogin;
        this.person = person;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Boolean getSuccessfulLogin() {
        return successfulLogin;
    }

    public void setSuccessfulLogin(Boolean successfulLogin) {
        this.successfulLogin = successfulLogin;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
