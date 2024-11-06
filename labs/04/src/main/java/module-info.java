module com.lab.lab {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lab.lab to javafx.fxml;
    exports com.lab.lab;
}