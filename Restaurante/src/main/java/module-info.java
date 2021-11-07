module com.restaurante {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.restaurante to javafx.fxml;
    exports com.restaurante;
}
