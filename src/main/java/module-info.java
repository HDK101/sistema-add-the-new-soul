module br.edu.ifsp.addthenewsoul {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.pdfbox;
    requires com.opencsv;
    requires bcrypt;


    opens br.edu.ifsp.addthenewsoul to javafx.fxml;
    opens br.edu.ifsp.addthenewsoul.application.io to br.edu.ifsp.addthenewsoul;
    opens br.edu.ifsp.addthenewsoul.domain.usecases.asset to br.edu.ifsp.addthenewsoul;
    exports br.edu.ifsp.addthenewsoul;
    exports br.edu.ifsp.addthenewsoul.application.io;
    exports br.edu.ifsp.addthenewsoul.domain.entities.asset;
    exports br.edu.ifsp.addthenewsoul.domain.usecases.asset;
}