module com.example.pow1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.pow1 to javafx.fxml;
    exports com.example.pow1;
}