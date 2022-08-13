package com.example.sistemabarbeirodoos4;

import com.example.sistemabarbeirodoos4.database.Database;
import com.example.sistemabarbeirodoos4.writers.CSV;
import com.example.sistemabarbeirodoos4.writers.CSVBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        List<CSVBean> data = Arrays.asList(new CSVBean("n1", "n2", "n3"));

        CSV.write(data);

        Database.getConnection();
        Statement statement = Database.getStatement();

        try {
            ResultSet rs = statement.executeQuery("SELECT DATE()");
            while(rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}