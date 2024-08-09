package com.example.rentcarerkos;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.PreparedStatement;
import java.util.Optional;

public class User {
    private Integer id;
    private Integer userId;
    private String ime;
    private String prezime;
    private String kontakt;
    private String jmpg;
    private String brojvozacke;
    private String drzava;
    private String email;

    public User(Integer userId, String ime, String prezime, String kontakt, String jmpg, String brojvozacke, String drzava, String email) {
        this.userId = userId;
        this.ime = ime;
        this.prezime = prezime;
        this.kontakt = kontakt;
        this.jmpg = jmpg;
        this.brojvozacke = brojvozacke;
        this.drzava = drzava;
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getKontakt() {
        return kontakt;
    }

    public String getJmpg() {
        return jmpg;
    }

    public String getBrojvozacke() {
        return brojvozacke;
    }

    public String getDrzava() {
        return drzava;
    }

    public String getEmail() {
        return email;
    }
}



