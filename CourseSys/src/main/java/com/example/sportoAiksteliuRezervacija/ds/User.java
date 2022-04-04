package com.example.sportoAiksteliuRezervacija.ds;

import com.example.sportoAiksteliuRezervacija.ds.enums.UserType;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String information;
    private UserType userType;
    private String pictureUrl;
    private String randomCode;
    @OneToMany
    private List<Schedule> reserved;

    @OneToMany
    private List<Reservation> userReservations;

    public User() {
    }

    public User(int id, String username, String password, String email, String information, UserType userType, String pictureUrl, List<Schedule> reserved) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.information = information;
        this.userType = userType;
        this.pictureUrl = pictureUrl;
        this.reserved = reserved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<Schedule> getReserved() {
        return reserved;
    }

    public void setReserved(List<Schedule> reserved) {
        this.reserved = reserved;
    }

    public List<Reservation> getUserReservations() {
        return userReservations;
    }

    public void setUserReservations(List<Reservation> userReservations) {
        this.userReservations = userReservations;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    @Override
    public String toString() {
        return id +":";
    }
}
