module com.example.rentcarerkos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.poi.ooxml;
    requires java.desktop;


    opens com.example.rentcarerkos to javafx.fxml;
    exports com.example.rentcarerkos;
}