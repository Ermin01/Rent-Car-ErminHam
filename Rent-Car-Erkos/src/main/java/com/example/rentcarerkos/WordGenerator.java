package com.example.rentcarerkos;
import javafx.collections.ObservableList;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordGenerator {
    public void generateWordDocument(ObservableList<UserRentData> userData, File file) {
        XWPFDocument document = new XWPFDocument();

        // Kreiranje tablice
        XWPFTable table = document.createTable(userData.size() + 1, 8);

        // Postavljanje zaglavlja tablice
        String[] headers = {"ID Car", "Brand", "Model", "First Name", "Last Name", "Cijena", "Izaberi Datum", "Datum Povratka"};
        XWPFTableRow headerRow = table.getRow(0);
        for (int i = 0; i < headers.length; i++) {
            XWPFTableCell cell = headerRow.getCell(i);
            cell.setText(headers[i]);
        }

        // Popunjavanje tablice s podacima
        for (int i = 0; i < userData.size(); i++) {
            UserRentData data = userData.get(i);
            XWPFTableRow row = table.getRow(i + 1);
            row.getCell(0).setText(data.getCarId());
            row.getCell(1).setText(data.getBrand());
            row.getCell(2).setText(data.getModel());
            row.getCell(3).setText(data.getIme());
            row.getCell(4).setText(data.getPrezime());
            row.getCell(5).setText(String.valueOf(data.getPrice()));
            row.getCell(6).setText(data.getDateRented().toString());
            row.getCell(7).setText(data.getDateReturn().toString());
        }

        // Spremanje dokumenta na odabranoj lokaciji
        try (FileOutputStream out = new FileOutputStream(file)) {
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
//public void availableAdd() {
//    String sql = "INSERT INTO car (car_id, brand, model, sixit, price, status, image, date)"
//            + "VALUES(?,?,?,?,?,?,?,?)";
//
//    connect = Database.connectDb();
//
//    try {
//        Alert alert;
//        if (availableCars_Id.getText().isEmpty()
//                || availableCars_brand.getText().isEmpty()
//                || availableCar_model.getText().isEmpty()
//                || availableCarId_sixit.getText().isEmpty()
//                || availablecar_price.getText().isEmpty()
//                || availableCar_status.getSelectionModel().getSelectedItem() == null
//                || getData.path == null || getData.path.isEmpty()) {
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Plese fill all blank fields");
//            alert.showAndWait();
//
//        } else if (availableCarId_sixit.getText().length() != 14) {
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("The 'sixit' field must contain exactly 14 characters");
//            alert.showAndWait();
//        } else {
//            prepare = connect.prepareStatement(sql);
//            prepare.setString(1, availableCars_Id.getText());
//            prepare.setString(2, availableCars_brand.getText());
//            prepare.setString(3, availableCar_model.getText());
//            prepare.setString(4, availableCarId_sixit.getText());
//            prepare.setString(5, availablecar_price.getText());
//            prepare.setString(6, (String) availableCar_status.getSelectionModel().getSelectedItem());
//
//            String uri = getData.path;
//            uri = uri.replace("\\", "\\\\");
//
//            prepare.setString(7, uri);
//
//            Date date = new Date();
//            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//
//            prepare.setString(8, String.valueOf(sqlDate));
//
//            prepare.executeUpdate();
//
//            alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Successfully added");
//            alert.showAndWait();
//
//            availableCarShowData();
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//}
//public void rentDisplayTotal(){
//    rentDate();
//    double price = 0;
//    String sql = "SELECT price, model FROM car WHERE model = '"
//            +rent_model.getSelectionModel().getSelectedItem()+"'";
//
//    connect = Database.connectDb();
//
//    try{
//        prepare = connect.prepareStatement(sql);
//        result = prepare.executeQuery();
//
//        if(result.next()){
//            price = result.getDouble("price");
//        }
//        // price * the count day you want to use the car
//        totalP = (price * countDate);
//        // DISPLAY TOTAL
//        rent_total.setText("$" + String.valueOf(totalP));
//
//        // Onemogući unos iznosa ako nije izračunat ukupni iznos
//        if(totalP == 0){
//            rent_amount.setDisable(true);
//        } else {
//            rent_amount.setDisable(false);
//        }
//
//    }catch(Exception e){e.printStackTrace();}
//
//}