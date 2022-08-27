module com.example.sistemabarbeirodoos4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.pdfbox;
    requires com.opencsv;
    requires bcrypt;


    opens com.example.sistemabarbeirodoos4 to javafx.fxml;
    opens com.example.sistemabarbeirodoos4.writers to com.example.sistemabarbeirodoos4;
    exports com.example.sistemabarbeirodoos4;
    exports com.example.sistemabarbeirodoos4.writers;
}