package br.edu.ifsp.addthenewsoul;

import br.edu.ifsp.addthenewsoul.application.io.CSV;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Local;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetCSV;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetCSVBean;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HelloApplication extends Application {
    public void HidekiTest() throws IOException {
        Employee employee = new Employee("asd", "asd", "asd", "asd", "asd", Role.EXECUTOR);
        Local local = new Local("asd", 1014);
        Asset asset = new Asset(1, "asd", employee, 123, "asd", local);

        List<Asset> data = Arrays.asList(asset, asset);

        AssetCSV assetCSV = new AssetCSV();
        assetCSV.write("file.csv", data);

        List<Asset> assetsFromCSV = assetCSV.read("file.csv");
        System.out.println(assetsFromCSV.get(0).getDamage());

        //AssetCSVBean csvBean = (AssetCSVBean) csv.get(0);
        //System.out.println(csvBean.toString());

//        Database.getConnection();
//        Statement statement = Database.getStatement();
//
//        try {
//            ResultSet rs = statement.executeQuery("SELECT DATE()");
//            while(rs.next()) {
//                System.out.println(rs.getString(1));
//            }
//
//        } catch (Exception ex) {
//            System.err.println(ex.getMessage());
//        }
//
//        PDDocument document = new PDDocument();
//        PDPage page = new PDPage();
//
//        int pageHeight = (int) page.getTrimBox().getHeight();
//        int pageWidth = (int) page.getTrimBox().getWidth();
//        PDPageContentStream contentStream = new PDPageContentStream(document, page);
//        contentStream.setStrokingColor(0f, 0f, 0f);
//
//        for(int i = 1; i < 10; i++) {
//            for(int j = 1; j < 10; j++) {
//                contentStream.addRect(50 * i, pageHeight - 50 * j, 50, 50);
//            }
//        }
//
//        contentStream.stroke();
//        contentStream.close();
//
//        document.addPage(page);
//
//        document.save("pdf.pdf");
//        document.close();
//
//        String password = "password";
//        String hash = BCrypt.withDefaults().hashToString(12, password.toCharArray());
//        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);
//
//        System.out.println(hash);
//        System.out.println(result.verified);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        HidekiTest();
    }

    public static void main(String[] args) {
        launch();
    }
}