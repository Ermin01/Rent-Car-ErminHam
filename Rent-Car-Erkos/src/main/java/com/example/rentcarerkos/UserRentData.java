package com.example.rentcarerkos;

import java.time.LocalDate;

public class UserRentData {
    private String carId;
    private String brand;
    private String model;
    private String ime;
    private String prezime;
    private Double price;
    private LocalDate dateRented;
    private LocalDate dateReturn;


    public UserRentData(String carId, String brand, String model, String ime, String prezime,Double price, LocalDate dateRented, LocalDate dateReturn) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.ime = ime;
        this.prezime = prezime;
        this.price = price;
        this.dateRented = dateRented;
        this.dateReturn = dateReturn;
    }


    // Get metode
    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getIme(){
        return ime;
    }
    public String getPrezime(){
        return prezime;
    }

    public Double getPrice(){
        return price;
    }
    public LocalDate getDateRented() {
        return dateRented;
    }

    public LocalDate getDateReturn() {
        return dateReturn;
    }
}