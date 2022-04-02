package com.example.sportoAiksteliuRezervacija.ds;

import com.example.sportoAiksteliuRezervacija.ds.enums.CityType;
import com.example.sportoAiksteliuRezervacija.ds.enums.CourtType;

import javax.persistence.*;
import java.util.List;


@Entity
public class Court{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String description;
    private CityType city;
    private CourtType type;
    private double cost;
    private String pictureUrl;
    @OneToMany
    private List<Schedule> schedules;

    public Court() {
    }

    public Court(int id, String name, String address, String description, CityType city, CourtType type, double cost, String pictureUrl, List<Schedule> schedules) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.city = city;
        this.type = type;
        this.cost = cost;
        this.pictureUrl = pictureUrl;
        this.schedules = schedules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CityType getCity() {
        return city;
    }

    public void setCity(CityType city) {
        this.city = city;
    }

    public CourtType getType() {
        return type;
    }

    public void setType(CourtType type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

}
