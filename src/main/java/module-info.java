module br.edu.ifsp.addthenewsoul {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.pdfbox;
    requires com.opencsv;
    requires bcrypt;


    opens br.edu.ifsp.addthenewsoul to javafx.fxml;
    opens br.edu.ifsp.addthenewsoul.application.writers to br.edu.ifsp.addthenewsoul;
    exports br.edu.ifsp.addthenewsoul;
    exports br.edu.ifsp.addthenewsoul.application.writers;
}