package com.example.rentcarerkos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Controller {

    @FXML
    private Button login;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;


    @FXML
    public ImageView slikaaaa;


    private double x=0;
    private double y=0;

    //Funkcija koja gasi X na ekranu
    public void hehe(){
        System.exit(0);
    }

    public void login(){
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = Database.connectDb();


        try{
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();

            Alert alert;

            if(username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Plese fill all bank fields");
                alert.showAndWait();

            }else{
                if(result.next()){

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfuly Login");
                    alert.showAndWait();

                    //obrisi
                    getData.username = username.getText();

                    login.getScene().getWindow().hide();

                    Parent root = FXMLLoader.load(getClass().getResource("Profiltwo.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);


                    //85 do 95 mozda izbaciit
                    root.setOnMouseReleased((MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getScreenY();
                    });
                    root.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });
                    stage.initStyle(StageStyle.TRANSPARENT);


                    stage.setScene(scene);
                    stage.show();

                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }
        }catch(Exception e) {e.printStackTrace();}
    }



    private void logoImage(){
        String imageView = "file:/C:/Users/Public/Documents/Rent-Car-Erkos/Auta/car.png";
        Image image = new Image(imageView);
        slikaaaa.setImage(image);
    }


    @FXML
    public void initialize() {
        // Pozivamo metodu za postavljanje slike na ImageView prilikom inicijalizacije kontrolera
        logoImage();
    }

}

