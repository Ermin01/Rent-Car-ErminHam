package com.example.rentcarerkos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;
import java.awt.Desktop;
public class  Dashboard implements Initializable  {
    @FXML
    private Button avaibleCars_btn;
    @FXML
    private TextField availableCarId_sixit;
    @FXML
    private ImageView availableCars_imageView;
    @FXML
    private TextField availableCar_model;
    @FXML
    private ComboBox<?> availableCar_status;
    @FXML
    private TextField availableCars_Id;
    @FXML
    private Button availableCars_add;
    @FXML
    private TextField availableCars_brand;
    @FXML
    private Button availableCars_clear;
    @FXML
    private TableColumn<Cardata, String> availableCars_col_brand;
    @FXML
    private TableColumn<Cardata, String> availableCars_col_carId;
    @FXML
    private TableColumn<Cardata, String> availableCars_col_model;

    @FXML
    private TableColumn<Cardata, String> availableCars_col_price;
    @FXML
    private TableColumn<Cardata, String> availableCars_col_sixit;
    @FXML
    private TableColumn<Cardata, String> availableCars_col_status;
    @FXML
    private Button availableCars_delete;
    @FXML
    private AnchorPane availableCars_form;
    @FXML
    private Button availableCars_importBtn;
    @FXML
    private TextField availableCars_search;
    @FXML
    private TableView<Cardata> availableCars_tableView;
    @FXML
    private Button availableCars_update;
    @FXML
    private TextField availablecar_price;
    @FXML
    private Button close;
    @FXML
    private Label home_availableCars;
    @FXML
    private Button home_btn;
    @FXML
    private BarChart<String, Number> home_incomeChart;
    @FXML
    private Label home_totalIncome;
    @FXML
    private LineChart<?, ?> home_customerData;
    @FXML
    private AnchorPane home_form;
    @FXML
    private Label home_totalCustomers;
    @FXML
    private AnchorPane main_form;
    @FXML
    private Button minisize;
    @FXML
    private Button rentBtn;
    @FXML
    private TextField rent_amount;
    @FXML
    private Label rent_balance;
    @FXML
    private TextField rent_brojvozacke;
    @FXML
    private Button rent_btn;
    @FXML
    private AnchorPane rent_form;
    @FXML
    private TextField rent_userId;
    @FXML
    private TableView<User> rent_tableView;
    @FXML
    private TableColumn<User, String> rent_col_userId;
    @FXML
    private TableColumn<User, String> rent_col_ime;
    @FXML
    private TableColumn<User, String> rent_col_prezime;
    @FXML
    private TableColumn<User, String> rent_col_kontakt;
    @FXML
    private TableColumn<User, String> rent_col_jmpg;
    @FXML
    private TableColumn<User, String> rent_col_brojvozacke;
    @FXML
    private TableColumn<User, String> rent_col_drzava;
    @FXML
    private TableColumn<User, String> rent_col_emailadresa;
    @FXML
    private TextField rent_drzava;
    @FXML
    private TextField rent_emailadresa;

//novo
    @FXML
    private TextField rent_ime;
    @FXML
    private TextField rent_jmpg;
    @FXML
    private TextField rent_kontakt;
    @FXML
    private TextField rent_prezime;
    //new
    @FXML
    private Button user_rent;
    @FXML
    private AnchorPane user_form;
    @FXML
    private TextField userSearch;

    //27.04
    @FXML
    private ComboBox<String> rent_carId;
    @FXML
    private  ComboBox<String> rent_model;
    @FXML
    private ComboBox<String> rent_brand;
    @FXML
    private ComboBox<String> rent_aktivnost;

    //209 mozes obrisati
    @FXML
    private TextField rent_imeime;

    @FXML TextField
            rent_prezimeprezime;

    @FXML
    private TableColumn<String, String> korisnik_id;

    @FXML
    private TableView<UserRentData> korisnik_table;

    @FXML
    private TableColumn<UserRentData, String> model_korisnik;

    @FXML
    private TableColumn<UserRentData ,String> brand_korisnik;


    //226 mozes obrisati
    @FXML
    private TableColumn<UserRentData, String> model_ime;

    @FXML
    private TableColumn<UserRentData, String> model_prezime;
    @FXML
    private TableColumn<UserRentData, String> dateRented;
    @FXML
    private TableColumn<UserRentData, String>  dateReturn;

    @FXML
    private TableColumn<UserRentData, String> model_cijena;
    //datum
    @FXML
    private DatePicker rent_dateRented;

    @FXML
    private DatePicker rent_dateReturn;
    //datum

    @FXML
    private ImageView logoContainer;

    @FXML
    private Label rent_total;

    @FXML
    private Label username;

    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();




    private double x = 0;
    private double y = 0;

//Bazapodataka tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private Image image;


public void availableAdd() {
    String sqlInsert = "INSERT INTO car (car_id, brand, model, sixit, price, status, image, date)"
            + "VALUES(?,?,?,?,?,?,?,?)";
    String sqlCheck = "SELECT MAX(car_id) FROM car";

    connect = Database.connectDb();

    try {
        Alert alert;

        // Check if any fields are empty
        if (availableCars_Id.getText().isEmpty()
                || availableCars_brand.getText().isEmpty()
                || availableCar_model.getText().isEmpty()
                || availableCarId_sixit.getText().isEmpty()
                || availablecar_price.getText().isEmpty()
                || availableCar_status.getSelectionModel().getSelectedItem() == null
                || getData.path == null || getData.path.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

            // Check if sixit field has exactly 2 characters
        } else if (availableCarId_sixit.getText().length() != 2) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("The 'sixit' field must contain exactly 2 characters");
            alert.showAndWait();

        } else {
            PreparedStatement checkPrepare = connect.prepareStatement(sqlCheck);
            ResultSet resultSet = checkPrepare.executeQuery();
            resultSet.next();
            int maxId = resultSet.getInt(1);

            int newId;
            try {
                newId = Integer.parseInt(availableCars_Id.getText());
            } catch (NumberFormatException e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Car ID must be a number");
                alert.showAndWait();
                return;
            }

            if (newId != maxId + 1) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Car ID must be " + (maxId + 1));
                alert.showAndWait();
            } else {
                PreparedStatement insertPrepare = connect.prepareStatement(sqlInsert);
                insertPrepare.setInt(1, newId);
                insertPrepare.setString(2, availableCars_brand.getText());
                insertPrepare.setString(3, availableCar_model.getText());
                insertPrepare.setString(4, availableCarId_sixit.getText());
                insertPrepare.setString(5, availablecar_price.getText());
                insertPrepare.setString(6, (String) availableCar_status.getSelectionModel().getSelectedItem());

                String uri = getData.path;
                uri = uri.replace("\\", "\\\\");
                insertPrepare.setString(7, uri);

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                insertPrepare.setString(8, String.valueOf(sqlDate));

                insertPrepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully added");
                alert.showAndWait();

                // Clear the input fields
                availableCars_Id.clear();
                availableCars_brand.clear();
                availableCar_model.clear();
                availableCarId_sixit.clear();
                availablecar_price.clear();
                availableCar_status.getSelectionModel().clearSelection();
                getData.path = null;

                availableCarShowData();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    private  final String [] listStatus = {"Dostupno" , "Nedostupno"};
    public void availableCarStatusList(){
        List<String> listS = new ArrayList<>();

        for(String data : listStatus){
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listS);
        availableCar_status.setItems(listData);
    }


    public void avaibleUpdate() {
        String sql = "UPDATE car SET brand=?, model=?, sixit=?, price=?, status=?, image=? WHERE car_id=?";

        connect = Database.connectDb();

        try {
            Alert alert;
            if (availableCars_Id.getText().isEmpty()
                    || availableCars_brand.getText().isEmpty()
                    || availableCar_model.getText().isEmpty()
                    || availableCarId_sixit.getText().isEmpty()
                    || availablecar_price.getText().isEmpty()
                    || availableCar_status.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Molimo ispunite sva potrebna polja.");
                alert.showAndWait();
            } else {
                // Provjera da li je ID vozila promijenjen
                Integer currentCarId = avaibleCarList.get(availableCars_tableView.getSelectionModel().getSelectedIndex()).getCarId();
                Integer newCarId = Integer.parseInt(availableCars_Id.getText());
                if (!currentCarId.equals(newCarId)) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Changing car ID is not allowed.");
                    alert.showAndWait();
                    return;
                }

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, availableCars_brand.getText());
                prepare.setString(2, availableCar_model.getText());
                prepare.setString(3, availableCarId_sixit.getText()); // Dodajemo šesticu u upit
                prepare.setString(4, availablecar_price.getText());
                prepare.setString(5, (String) availableCar_status.getSelectionModel().getSelectedItem());

                // Provjeravamo je li odabrana nova slika
                if (getData.path != null && !getData.path.isEmpty()) {
                    String uri = getData.path.replace("\\", "\\\\");
                    prepare.setString(6, uri);
                } else {
                    // Ako nije odabrana nova slika, zadržavamo postojeću sliku u bazi podataka
                    prepare.setString(6, avaibleCarList.get(availableCars_tableView.getSelectionModel().getSelectedIndex()).getImage());
                }
                prepare.setInt(7, newCarId);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully updated!");
                alert.showAndWait();

                availableCars_Id.clear();
                availableCars_brand.clear();
                availableCar_model.clear();
                availableCarId_sixit.clear();
                availablecar_price.clear();
                availableCar_status.getSelectionModel().clearSelection();
                getData.path = null;

                // Nakon ažuriranja, očisti selekciju ComboBox-a za status
                availableCar_status.getSelectionModel().clearSelection();

                // Očisti polja
                avaibleClear();

                // Prikazuje ponovno podatke
                availableCarShowData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void avaibleSerach() {
        FilteredList<Cardata> filter = new FilteredList<>(avaibleCarList, e -> true);

        availableCars_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(PrediateFlowerData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }


                String searchKey = newValue.toLowerCase();

                if (PrediateFlowerData.getCarId().toString().contains(searchKey)) {
                    return true;
                } else if (PrediateFlowerData.getBrand().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (PrediateFlowerData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (PrediateFlowerData.getModel().toString().contains(searchKey)) {
                    return true;}
                else if(PrediateFlowerData.getSixit().toString().toLowerCase().contains(searchKey)){
                    return true;}
                else {
                    return false;
                }
            });
            SortedList<Cardata> sortList = new SortedList<>(filter);

            sortList.comparatorProperty().bind(availableCars_tableView.comparatorProperty());

            availableCars_tableView.setItems(sortList);
        });
    }
    public void avaiblecarDelete() {
        String carId = availableCars_Id.getText();

        if (carId.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select a car to delete.");
            alert.showAndWait();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation Message");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to DELETE car ID: " + carId + "?");
        Optional<ButtonType> option = confirmationAlert.showAndWait();

        if (option.isPresent() && option.get().equals(ButtonType.OK)) {
            String rentDeleteSql = "DELETE FROM useruser_rent WHERE car_id = ?";
            String carDeleteSql = "DELETE FROM car WHERE car_id = ?";
            String reorderSql = "UPDATE car SET car_id = car_id - 1 WHERE car_id > ?";
            connect = Database.connectDb();

            try {
                // Prvo brišemo iz tablice za iznajmljivanje
                try (PreparedStatement rentDeleteStatement = connect.prepareStatement(rentDeleteSql)) {
                    rentDeleteStatement.setString(1, carId);
                    rentDeleteStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error Message");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Error occurred while deleting rental information.");
                    errorAlert.showAndWait();
                    return;
                }

                // Zatim brišemo iz tablice automobila
                try (PreparedStatement carDeleteStatement = connect.prepareStatement(carDeleteSql)) {
                    carDeleteStatement.setString(1, carId);
                    carDeleteStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error Message");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Error occurred while deleting car information.");
                    errorAlert.showAndWait();
                    return;
                }

                // Reorder the car IDs
                try (PreparedStatement reorderStatement = connect.prepareStatement(reorderSql)) {
                    reorderStatement.setString(1, carId);
                    reorderStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error Message");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Error occurred while reordering car IDs.");
                    errorAlert.showAndWait();
                    return;
                }

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Successfully Deleted and Reordered!");
                successAlert.showAndWait();

                // Clear the input fields
                availableCars_Id.clear();
                availableCars_brand.clear();
                availableCar_model.clear();
                availableCarId_sixit.clear();
                availablecar_price.clear();
                availableCar_status.getSelectionModel().clearSelection();
                getData.path = null;

                availableCarShowData();
                availableCarStatusList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private boolean isCarRented(String carId) {
        String sql = "SELECT * FROM useruser_rent WHERE car_id = ?";
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, carId);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isRented = resultSet.next(); // If any record is returned, the car is rented
            System.out.println("Car with ID " + carId + " is rented: " + isRented);
            return isRented;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred, assume car is not rented
        }
    }


    public void avaibleCarImportImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Open image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));


        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file !=null){


            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 150, 176, false, true);
            availableCars_imageView.setImage(image);
        }
    }
    public void avaibleClear(){
        availableCars_Id.setText("");
        availableCars_brand.setText("");
        availableCar_model.setText("");
        availableCar_status.getSelectionModel().clearSelection();
        availablecar_price.setText("");
        availableCarId_sixit.setText("");

        getData.path = "";

        availableCars_imageView.setImage(null);
    }


    public ObservableList<Cardata> availableCarListData(){

        ObservableList<Cardata> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM car";
        connect = Database.connectDb();


        try {
            Cardata carD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                carD = new Cardata(result.getInt("car_id")
                        ,result.getString("brand")
                        ,result.getString("model")
                        ,result.getString("sixit")
                        ,result.getDouble("price")
                        ,result.getString("status")
                        ,result.getString("image")
                        ,result.getDate("date"));
                listData.add(carD);
            }
        }catch (Exception e){e.printStackTrace();}
        return listData;
    }
    private ObservableList<Cardata> avaibleCarList;
    public void availableCarShowData() {
        avaibleCarList = availableCarListData();

        availableCars_col_carId.setCellValueFactory(new PropertyValueFactory<>("carId"));
        availableCars_col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        availableCars_col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
        availableCars_col_sixit.setCellValueFactory(new PropertyValueFactory<>("sixit"));
        availableCars_col_status.setCellValueFactory(new PropertyValueFactory<>("price"));
        availableCars_col_price.setCellValueFactory(new PropertyValueFactory<>("status"));

        availableCars_tableView.setItems(avaibleCarList);
    }



    public void avaibleCarSelect(){
        Cardata carD = availableCars_tableView.getSelectionModel().getSelectedItem();
        int num = availableCars_tableView.getSelectionModel().getSelectedIndex();

        if(( num-1 ) < -1) return;

        availableCars_Id.setText(String.valueOf(carD.getCarId()));
        availableCars_brand.setText(carD.getBrand());
        availableCar_model.setText(carD.getModel());
        availableCarId_sixit.setText(carD.getSixit());

        availablecar_price.setText(String.valueOf(carD.getPrice()));

        String uri = "file:" + carD.getImage();

        image = new Image(uri, 150, 175 , false, true);
        availableCars_imageView.setImage(image);

    }


    public void displayUsername(){
        String user = getData.username;

        username.setText(user.substring(0,1) .toUpperCase() + user.substring(1));
    }


//novo19.04.02024
public ObservableList<Cardata> rentCarListData() {

    ObservableList<Cardata> listData = FXCollections.observableArrayList();

    String sql = "SELECT * FROM car";

    connect = Database.connectDb();

    try {
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();

        Cardata carD;

        while (result.next()) {
            carD = new Cardata(result.getInt("car_id")
                    , result.getString("brand")
                    , result.getString("model")
                    , null // assuming sixit is not needed here, you can pass null
                    , result.getDouble("price")
                    , result.getString("status")
                    , result.getString("image")
                    , result.getDate("date"));

            listData.add(carD);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return listData;
}

    public void homeavailableCars() {
        String sql = "SELECT COUNT(car_id) AS car_count FROM car WHERE status = 'Dostupno'";

        connect = Database.connectDb();

        int countAc = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countAc = result.getInt("car_count");
            }
            home_availableCars.setText(String.valueOf(countAc));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //novo
    public void switchform(ActionEvent event){

        if (event.getSource() == home_btn) {

            home_form.setVisible(true);
            availableCars_form.setVisible(false);
            rent_form.setVisible(false);
            user_form.setVisible(false);
//            home_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #2087d0, #696d72);");
//            avaibleCars_btn.setStyle("-fx-background-color:transparent");
//            user_rent.setStyle("-fx-background-color:transparent");

        }else if(event.getSource() == avaibleCars_btn){
            home_form.setVisible(false);
            availableCars_form.setVisible(true);
            rent_form.setVisible(false);
            user_form.setVisible(false);

            homeavailableCars();
           homeTotalCustomers();
           home_totalIncome();
            //resetTotalIncome();

//            avaibleCars_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #2087d0, #696d72);");
//            home_btn.setStyle("-fx-background-color:transparent");
            availableCarShowData();
            availableCarStatusList();
            avaibleSerach();

        }else if(event.getSource() == rent_btn){
            home_form.setVisible(false);
            availableCars_form.setVisible(false);
            rent_form.setVisible(true);
            user_form.setVisible(false);

//            rent_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #2087d0, #696d72);");
//            home_btn.setStyle("-fx-background-color:transparent");
//            user_rent.setStyle("-fx-background-color:transparent");
            availableCarShowDataa();

        }else if(event.getSource() == user_rent){
            home_form.setVisible(false);
            availableCars_form.setVisible(false);
            rent_form.setVisible(false);
            user_form.setVisible(true);

//            rent_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #2087d0, #696d72);");
//            home_btn.setStyle("-fx-background-color:transparent");
//            user_rent.setStyle("-fx-background-color:transparent");
            serachUser();

            rentCarCarId();
            rentCarBrand();
            rentCarModel();


        }

    }

public void rentCarCarId() {
    String sql = "SELECT * FROM car WHERE status = 'Dostupno'";

    connect = Database.connectDb();

    try {
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();

        ObservableList<String> carIds = FXCollections.observableArrayList();
        ObservableList<String> statuses = FXCollections.observableArrayList();

        while (result.next()) {
            carIds.add(result.getString("car_id"));
            statuses.add(result.getString("status"));
        }

        rent_carId.setItems(carIds);


        rent_carId.setOnAction(e -> {
            int selectedIndex = rent_carId.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < statuses.size()) {
                String selectedStatus = statuses.get(selectedIndex);
                rent_aktivnost.setValue(selectedStatus);
                rentCarBrand();
                rentCarModel(); // Pozovemo rentCarModel() kada je odabran ID automobila
            }
        });

        rent_aktivnost.setItems(statuses);

    } catch (Exception e) {
        e.printStackTrace();
    }
}



 public void rentCarBrand() {
        String selectedCarId = rent_carId.getSelectionModel().getSelectedItem();

        if (selectedCarId != null) {
            String sql = "SELECT * FROM car WHERE car_id = '" + selectedCarId + "'";

            connect = Database.connectDb();

            try {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                ObservableList listData = FXCollections.observableArrayList();

                while (result.next()) {
                    listData.add(result.getString("brand"));
                }

                rent_brand.setItems(listData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void rentCarModel() {
        String selectedCarId = rent_carId.getSelectionModel().getSelectedItem();

        if (selectedCarId != null) {
            String sql = "SELECT * FROM car WHERE car_id = '" + selectedCarId + "'";

            connect = Database.connectDb();

            try {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                ObservableList listData = FXCollections.observableArrayList();

                while (result.next()) {
                    listData.add(result.getString("model"));
                }

                rent_model.setItems(listData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    public void rentCarToUser() {
        String carId = rent_carId.getValue(); // Dohvat ID-a automobila
        String brand = rent_brand.getValue(); // Dohvat marke automobila
        String model = rent_model.getValue(); // Dohvat modela automobila
        String ime = rent_imeime.getText(); // Dohvat imena korisnika
        String prezime = rent_prezimeprezime.getText(); // Dohvat prezimena korisnika

        LocalDate dateRented = rent_dateRented.getValue(); // Dohvat datuma najma
        LocalDate dateReturn = rent_dateReturn.getValue(); // Dohvat datuma povratka

        // Provjera jesu li svi potrebni podaci uneseni
        if (carId == null || brand == null || model == null || dateRented == null || dateReturn == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all required information.");
            alert.showAndWait();
            return;
        }
        Alert alert;
         if (rent_imeime.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Unesite ime");
            alert.showAndWait();
            return;
        }
         else if(rent_prezimeprezime.getText().isEmpty()){
             alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error Message");
             alert.setHeaderText(null);
             alert.setContentText("Unesite prezime");
             alert.showAndWait();
             return;
         }

        // SQL upit za unos podataka o najmu automobila
        String sqlRentCar = "INSERT INTO useruser_rent (car_id, brand, model, ime, prezime, dateRented, dateReturn, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        // SQL upit za ažuriranje dostupnosti automobila
        String sqlUpdateAvailability = "UPDATE car SET status = 'Nedostupno' WHERE car_id = ?";
        // SQL upit za dohvaćanje cijene automobila
        String sqlGetPrice = "SELECT price FROM car WHERE car_id = ?";

        connect = Database.connectDb();

        try {
            // Dohvat cijene automobila
            double carPrice = 0;
            try {
                prepare = connect.prepareStatement(sqlGetPrice);
                prepare.setString(1, carId);
                result = prepare.executeQuery();

                if (result.next()) {
                    carPrice = result.getDouble("price");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Unos podataka o najmu automobila
            prepare = connect.prepareStatement(sqlRentCar);
            prepare.setString(1, carId);
            prepare.setString(2, brand);
            prepare.setString(3, model);
            prepare.setString(4, ime);
            prepare.setString(5, prezime);
            prepare.setDate(6, java.sql.Date.valueOf(dateRented));
            prepare.setDate(7, java.sql.Date.valueOf(dateReturn));
            // Unos ukupne cijene automobila
            prepare.setDouble(8, carPrice * countDate);
            // Izvršavanje upita
            prepare.executeUpdate();

            // Ažuriranje dostupnosti automobila
            prepare = connect.prepareStatement(sqlUpdateAvailability);
            prepare.setString(1, carId);
            prepare.executeUpdate();

            // Prikaz uspješne poruke
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Information Message");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Automobil je uspješno iznajmljen korisniku.");
            successAlert.showAndWait();

            // Ažuriranje tablice s podacima o najmu
            populateUserRentData();
            // Brisanje polja nakon unosa
            clearFields();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void clearFields() {
        rent_carId.getSelectionModel().clearSelection();
        rent_aktivnost.getSelectionModel().clearSelection();
        rent_imeime.clear();
        rent_prezimeprezime.clear();
        rent_brand.getItems().clear();
        rent_model.getItems().clear();

        rent_dateRented.setValue(null);
        rent_dateReturn.setValue(null);

        rent_amount.setText("0");
        //posle obrisiti
    }

    public void populateUserRentData() {
        String sql = "SELECT * FROM useruser_rent";

        connect = Database.connectDb();
        ObservableList<UserRentData> userData = FXCollections.observableArrayList();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                String carId = result.getString("car_id");
                String brand = result.getString("brand");
                String model = result.getString("model");
                String ime = result.getString("ime");
                String prezime = result.getString("prezime");
                Double price = result.getDouble("price");
//                LocalDate dateRented = result.getDate("dateRented") != null ? result.getDate("dateRented").toLocalDate() : null;
//                LocalDate dateReturn = result.getDate("dateReturn") != null ? result.getDate("dateReturn").toLocalDate() : null;
                LocalDate dateRented = result.getDate("dateRented").toLocalDate();
                LocalDate dateReturn = result.getDate("dateReturn").toLocalDate();
                userData.add(new UserRentData(carId, brand, model, ime, prezime, price, dateRented, dateReturn));
            }

            korisnik_table.setItems(userData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setupUserRentTable() {
        korisnik_id.setCellValueFactory(new PropertyValueFactory<>("carId"));
        brand_korisnik.setCellValueFactory(new PropertyValueFactory<>("brand"));
        model_korisnik.setCellValueFactory(new PropertyValueFactory<>("model"));
        model_ime.setCellValueFactory(new PropertyValueFactory<>("ime"));
        model_prezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        model_cijena.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateRented.setCellValueFactory(new PropertyValueFactory<>("dateRented"));
        dateReturn.setCellValueFactory(new PropertyValueFactory<>("dateReturn"));

    }

    public void refreshTable() {

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Informativna poruka");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Uspijesno refreshovana Tabela.");
        successAlert.showAndWait();

        korisnik_table.getItems().clear();
        populateUserRentData();
    }

public void sendWordDocument() {
    WordGenerator wordGenerator = new WordGenerator();
    ObservableList<UserRentData> userData = korisnik_table.getItems();

    // Koristimo FileChooser za odabir lokacije i naziva datoteke
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save Document");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word Document", "*.docx"));
    File file = fileChooser.showSaveDialog(new Stage());

    if (file != null) {
        // Generisanje i spremanje dokumenta na odabranoj lokaciji
        wordGenerator.generateWordDocument(userData, file);
    }
}
public void UserWord(){
    WordUser wordUser = new WordUser();
    ObservableList<User> userWord = rent_tableView.getItems();
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save Document");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word Document", "*.docx"));
    File file = fileChooser.showSaveDialog(new Stage());

    if (file != null) {
        // Generate and save the document to the selected location
        wordUser.userWordDocument(userWord, file);
    }
}

private int countDate;
    public void rentDate(){
        Alert alert;
        if(rent_carId.getSelectionModel().getSelectedItem() == null
                || rent_brand.getSelectionModel().getSelectedItem() == null
                || rent_model.getSelectionModel().getSelectedItem() == null
               // || rent_imeime.getText().isEmpty()
        ){
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Something wrong :3");
//            alert.showAndWait();

            rent_dateRented.setValue(null);
            rent_dateReturn.setValue(null);

        }else{

            if(rent_dateReturn.getValue().isAfter(rent_dateRented.getValue())){
                // COUNT THE DAY
                countDate = rent_dateReturn.getValue().compareTo(rent_dateRented.getValue());
            }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something wrong :3");
                alert.showAndWait();
                rent_dateReturn.setValue(rent_dateRented.getValue().plusDays(1));
            }
        }
    }

    private double totalP;

public void rentDisplayTotal(){
    rentDate();
    double price = 0;
    String sql = "SELECT price, model FROM car WHERE model = '"
            +rent_model.getSelectionModel().getSelectedItem()+"'";

    connect = Database.connectDb();

    try{
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();

        if(result.next()){
            price = result.getDouble("price");
        }
        // price * the count day you want to use the car
        totalP = (price * countDate);
        // DISPLAY TOTAL
        rent_total.setText("$" + String.valueOf(totalP));

        // Resetiraj saldo na 0
        rent_balance.setText("$0.0");

        // Onemogući unos iznosa ako nije izračunat ukupni iznos
        if(totalP == 0){
            rent_amount.setDisable(true);
        } else {
            rent_amount.setDisable(false);
        }

    }catch(Exception e){e.printStackTrace();}

}

    //doradi ili obrisi
    private double amount;
    private double balance;
    public void rentAmount(){
        Alert alert;
        if(totalP == 0 || rent_amount.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid :3");
            alert.showAndWait();

            rent_amount.setText("");
        }else{
            amount = Double.parseDouble(rent_amount.getText());

            if(amount >= totalP){
                balance = (amount - totalP);
                rent_balance.setText("$" + String.valueOf(balance));
            }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid :3");
                alert.showAndWait();

                rent_amount.setText("");
            }

        }

    }









    //23.04.2024
    //prvo ovo se radi
    // pa se sljedece

    public ObservableList<User> rentCar() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM user";
        connect = Database.connectDb();

        try {
            User user;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                user = new User(
                        result.getInt("user_id"),
                        result.getString("ime"),
                        result.getString("prezime"),
                        result.getString("kontakt"),
                        result.getString("jmpg"),
                        result.getString("brojvozacke"),
                        result.getString("drzava"),
                        result.getString("email")
                );
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }


     ObservableList<User> UserList;
    public void availableCarShowDataa() {
        UserList = rentCar();

        rent_col_userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        rent_col_ime.setCellValueFactory(new PropertyValueFactory<>("ime"));
        rent_col_prezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        rent_col_kontakt.setCellValueFactory(new PropertyValueFactory<>("kontakt"));
        rent_col_jmpg.setCellValueFactory(new PropertyValueFactory<>("jmpg"));
        rent_col_brojvozacke.setCellValueFactory(new PropertyValueFactory<>("brojvozacke"));
        rent_col_drzava.setCellValueFactory(new PropertyValueFactory<>("drzava"));
        rent_col_emailadresa.setCellValueFactory(new PropertyValueFactory<>("email"));

        rent_tableView.setItems(UserList);
    }

public void rentAdd() {
    String sqlCheck = "SELECT COUNT(*) FROM user WHERE user_id = ?";
    String sqlMaxId = "SELECT MAX(user_id) FROM user";
    String sqlInsert = "INSERT INTO user (user_id, ime, prezime, kontakt, jmpg, brojvozacke, drzava, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    connect = Database.connectDb();

    try {
        // Checking for empty fields
        Alert alert;
        if (rent_userId.getText().isEmpty()
                || rent_ime.getText().isEmpty()
                || rent_prezime.getText().isEmpty()
                || rent_kontakt.getText().isEmpty()
                || rent_jmpg.getText().isEmpty()
                || rent_brojvozacke.getText().isEmpty()
                || rent_drzava.getText().isEmpty()
                || rent_emailadresa.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            return;
        } else if (rent_jmpg.getText().length() != 1) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Unesi JMBG da je 13");
            alert.showAndWait();
            return;
        } else if (rent_brojvozacke.getText().length() != 1) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Unesi Broj vozacke da je 13");
            alert.showAndWait();
            return;
        }

        // Ensure user_id is an integer
        int userId;
        try {
            userId = Integer.parseInt(rent_userId.getText());
        } catch (NumberFormatException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("User ID must be an integer!");
            alert.showAndWait();
            return;
        }

        // Get the maximum user_id
        PreparedStatement maxIdStatement = connect.prepareStatement(sqlMaxId);
        ResultSet maxIdResultSet = maxIdStatement.executeQuery();
        maxIdResultSet.next();
        int maxUserId = maxIdResultSet.getInt(1);

        // Check if the new user_id is the next in sequence
        if (userId != maxUserId + 1) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("User ID must be " + (maxUserId + 1));
            alert.showAndWait();
            return;
        }

        // Check if user_id already exists (this is redundant now, but we'll keep it for safety)
        PreparedStatement checkStatement = connect.prepareStatement(sqlCheck);
        checkStatement.setInt(1, userId);
        ResultSet resultSet = checkStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        if (count > 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("User ID already exists!");
            alert.showAndWait();
            return;
        }

        // Insert new user
        prepare = connect.prepareStatement(sqlInsert);
        prepare.setInt(1, userId);
        prepare.setString(2, rent_ime.getText());
        prepare.setString(3, rent_prezime.getText());
        prepare.setString(4, rent_kontakt.getText());
        prepare.setString(5, rent_jmpg.getText());
        prepare.setString(6, rent_brojvozacke.getText());
        prepare.setString(7, rent_drzava.getText());
        prepare.setString(8, rent_emailadresa.getText());

        prepare.executeUpdate();

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully added!");
        alert.showAndWait();

        // Refreshing the table view after adding data
        availableCarShowDataa();
        // Refreshing the user table view after adding data
        rent_tableView.setItems(rentCar());
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public void rentDelete() {
        String userId = rent_userId.getText();

        if (userId.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Odaberite osobu za brisanje.");
            alert.showAndWait();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation Message");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Jeste li sigurni da želite IZBRISATI korisnika ID: " + userId + "?");
        Optional<ButtonType> option = confirmationAlert.showAndWait();

        if (option.isPresent() && option.get().equals(ButtonType.OK)) {
            connect = Database.connectDb();
            String sqlDelete = "DELETE FROM user WHERE user_id = ?";
            String sqlUpdate = "UPDATE user SET user_id = user_id - 1 WHERE user_id > ?";

            try (PreparedStatement deleteStatement = connect.prepareStatement(sqlDelete);
                 PreparedStatement updateStatement = connect.prepareStatement(sqlUpdate)) {

                // Obriši korisnika
                deleteStatement.setString(1, userId);
                deleteStatement.execute();

                // Ažuriraj ID-ove korisnika
                updateStatement.setString(1, userId);
                updateStatement.execute();

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Successfully Deleted!");
                successAlert.showAndWait();

                // Pozovite metodu za ažuriranje UI-a ili izvršavanje drugih potrebnih radnji
                availableCarShowDataa();

                // Očistite polja
                rent_userId.clear();
                rent_ime.clear();
                rent_prezime.clear();
                rent_kontakt.clear();
                rent_jmpg.clear();
                rent_brojvozacke.clear();
                rent_drzava.clear();
                rent_emailadresa.clear();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void userClear(){
        rent_userId.clear();
        rent_ime.clear();
        rent_prezime.clear();
        rent_kontakt.clear();
        rent_jmpg.clear();
        rent_brojvozacke.clear();
        rent_drzava.clear();
        rent_emailadresa.clear();

    }
    public void userUpdate() {
        // SQL query to update user information
        String sql = "UPDATE user SET ime=?, prezime=?, kontakt=?, jmpg=?, brojvozacke=?, drzava=?, email=? WHERE user_id=?";

        // Get the database connection
        connect = Database.connectDb();

        try {
            Alert alert;

            // Check if any required field is empty
            if (rent_userId.getText().isEmpty()
                    || rent_ime.getText().isEmpty()
                    || rent_prezime.getText().isEmpty()
                    || rent_kontakt.getText().isEmpty()
                    || rent_jmpg.getText().isEmpty()
                    || rent_brojvozacke.getText().isEmpty()
                    || rent_drzava.getText().isEmpty()
                    || rent_emailadresa.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all required fields.");
                alert.showAndWait();
                return; // Exit the method if any required field is empty
            }

            // Prepare the SQL statement
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, rent_ime.getText());
            prepare.setString(2, rent_prezime.getText());
            prepare.setString(3, rent_kontakt.getText());
            prepare.setString(4, rent_jmpg.getText()); // Set the value for jmpg
            prepare.setString(5, rent_brojvozacke.getText());
            prepare.setString(6, rent_drzava.getText());
            prepare.setString(7, rent_emailadresa.getText());
            prepare.setString(8, rent_userId.getText());

            // Execute the SQL update statement
            prepare.executeUpdate();

            // Show success message
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully updated!");
            alert.showAndWait();


            userClear();


            availableCarShowDataa();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void serachUser(){

        FilteredList<User> filter = new FilteredList<>(UserList, e -> true);

        userSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(PredicateuserData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchUser = newValue.toLowerCase();

                if (PredicateuserData.getIme().toString().toLowerCase().contains(searchUser)){
                    return true;
                } else if (PredicateuserData.getPrezime().toString().toLowerCase().contains(searchUser)) {
                    return true;
                } else if (PredicateuserData.getBrojvozacke().toString().toLowerCase().contains(searchUser)){
                    return true;
                } else if (PredicateuserData.getJmpg().toString().toLowerCase().contains(searchUser)) {
                    return true;
                } else if (PredicateuserData.getEmail().toString().toLowerCase().contains(searchUser)){
                        return true;
                }
                else{return false;

                }
            });
                SortedList<User> userList = new SortedList<>(filter);
                userList.comparatorProperty().bind(rent_tableView.comparatorProperty());

                rent_tableView.setItems(userList);
        });
    }
    public void UserSelect() {
        User user = rent_tableView.getSelectionModel().getSelectedItem();
        int num = rent_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) return;

        rent_userId.setText(String.valueOf(user.getUserId()));
        rent_ime.setText(String.valueOf(user.getIme()));
        rent_prezime.setText(String.valueOf(user.getPrezime()));
        rent_kontakt.setText(String.valueOf(user.getKontakt()));
        rent_jmpg.setText(String.valueOf(user.getJmpg()));
        rent_brojvozacke.setText(String.valueOf(user.getBrojvozacke()));
        rent_drzava.setText(String.valueOf(user.getDrzava()));
        rent_emailadresa.setText(String.valueOf(user.getEmail()));
    }



    public void homeTotalCustomers(){

        String sql = "SELECT COUNT(id) FROM user";

        int countTC = 0;

        connect = Database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                countTC = result.getInt("COUNT(id)");
            }
            home_totalCustomers.setText(String.valueOf(countTC));
        }catch(Exception e){e.printStackTrace();}

    }

@FXML
private void openLink(ActionEvent event) throws URISyntaxException, IOException {
    System.out.println("Link Click");
    Desktop.getDesktop().browse(new URI("https://github.com/Ermin01"));
    }


    public void close(){
        System.exit(0);
    }
    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    private void showLogoImage() {
        // Path to the image
        String imagePath = "file:///C:/Users/Public/Documents/Rent-Car-Erkos/Auta/logo.png";
        // Load the image
        Image image = new Image(imagePath);
        // Set the image in ImageView
        logoContainer.setImage(image);
    }

//novo

    public void home_totalIncome() {
        String sql = "SELECT SUM(price) AS totalIncome FROM useruser_rent";

        connect = Database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                double totalIncome = result.getDouble("totalIncome");

                // Update BarChart with total income data
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>("Total Income", totalIncome));
                home_incomeChart.getData().add(series);

                // Postavljanje boje serije na ljubičastu (purple)
                for (XYChart.Data<String, Number> data : series.getData()) {
                    Node bar = data.getNode();
                    bar.setStyle("-fx-bar-fill: purple;");
                }

                // Assuming you have a label or text field named home_totalIncomeLabel to display the total income
                home_totalIncome.setText("$" + String.format("%.2f", totalIncome));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //UPDATE useruser_rent SET price = 0.0;

    public void logut(){
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Configuration message");
            alert.setHeaderText(null);
            alert.setContentText("Jeste li sigurni da se želite odjaviti");
            Optional<ButtonType> opiton = alert.showAndWait();

            if (opiton.get().equals(ButtonType.OK)){

                close.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });
                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();



            }
        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();
        homeavailableCars();
        homeTotalCustomers();
        home_totalIncome();
        availableCarShowData();
        availableCarStatusList();
        avaibleSerach();


        availableCarShowDataa();

        serachUser();
        rentCarCarId();
        rentCarBrand();
        rentCarModel();

        setupUserRentTable();
        populateUserRentData();
        showLogoImage();

    }
}

