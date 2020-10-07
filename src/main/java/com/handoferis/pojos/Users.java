package com.handoferis.pojos;

import java.util.Date;
import java.util.UUID;

//@Entity
public class Users {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(insertable=false, updatable = false, nullable = false, columnDefinition = "VARCHAR(255")
    private UUID id;
    private String username;
    private String email;
    private String phone;
    private String state;
    private String zip;
    private String country;
    private Date registrationDate;

    public Users() {
    }

    public Users(UUID id, String username, String email, String phone, String state, String zip, String country, Date registrationDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.registrationDate = registrationDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
