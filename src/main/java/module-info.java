module com.example.sistemabarbeirodoos4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sistemabarbeirodoos4 to javafx.fxml;
    exports com.example.sistemabarbeirodoos4;
}