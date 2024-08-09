package com.example.rentcarerkos;

import java.util.Date;

public class Cardata {

    private Integer carId;
    private String brand;
    private String model;
    private String sixit;
    private Double price;
    private String status;
    private Date date;
    private String image;


    public Cardata(Integer carId, String brand, String model, String sixit, Double price, String status, String image,  Date date)    {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.sixit = sixit;
        this.price = price;
        this.status = status;
        this.date = date;
        this.image = image;

    }

    public Integer getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSixit() {
        return sixit;
    }

    public Double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }




    //novo sto posle moze se izbaciit



}
