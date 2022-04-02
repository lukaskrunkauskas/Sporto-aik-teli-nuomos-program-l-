package com.example.sportoAiksteliuRezervacija.ds;

import com.example.sportoAiksteliuRezervacija.ds.enums.UserType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cardHolder;
    private int cardNumber;
    private int cardCvc;
    private LocalDate cardExpiration;
    @OneToOne
    private Court court;

    public Reservation() {
    }

    public Reservation(int id, String cardHolder, int cardNumber, int cardCvc, LocalDate cardExpiration, Court court) {
        this.id = id;
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.cardCvc = cardCvc;
        this.cardExpiration = cardExpiration;
        this.court = court;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardCvc() {
        return cardCvc;
    }

    public void setCardCvc(int cardCvc) {
        this.cardCvc = cardCvc;
    }

    public LocalDate getCardExpiration() {
        return cardExpiration;
    }

    public void setCardExpiration(LocalDate cardExpiration) {
        this.cardExpiration = cardExpiration;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }
}
