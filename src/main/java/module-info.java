module br.edu.ifsp.addthenewsoul {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.pdfbox;
    requires com.opencsv;
    requires static lombok;
    requires bcrypt;


    opens br.edu.ifsp.addthenewsoul to javafx.fxml;
    exports br.edu.ifsp.addthenewsoul;
    exports br.edu.ifsp.addthenewsoul.application.io;
    exports br.edu.ifsp.addthenewsoul.domain.entities.asset;
    exports br.edu.ifsp.addthenewsoul.domain.usecases.asset;
    exports br.edu.ifsp.addthenewsoul.domain.usecases.inventory;
    exports br.edu.ifsp.addthenewsoul.application.view;
    opens br.edu.ifsp.addthenewsoul.application.view to javafx.fxml;
    exports br.edu.ifsp.addthenewsoul.application.controller;
    opens br.edu.ifsp.addthenewsoul.application.controller to javafx.fxml;
}