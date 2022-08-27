package com.example.sistemabarbeirodoos4;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.sistemabarbeirodoos4.database.Database;
import com.example.sistemabarbeirodoos4.writers.CSV;
import com.example.sistemabarbeirodoos4.writers.CSVBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

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

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();

        int pageHeight = (int) page.getTrimBox().getHeight();
        int pageWidth = (int) page.getTrimBox().getWidth();
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setStrokingColor(0f, 0f, 0f);

        for(int i = 1; i < 10; i++) {
            for(int j = 1; j < 10; j++) {
                contentStream.addRect(50 * i, pageHeight - 50 * j, 50, 50);
            }
        }

        contentStream.stroke();
        contentStream.close();

        document.addPage(page);

        document.save("pdf.pdf");
        document.close();

        String password = "password";
        String hash = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);

        System.out.println(hash);
        System.out.println(result.verified);
    }

    public static void main(String[] args) {
        launch();
    }
}