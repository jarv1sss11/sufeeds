module com.segera.sufeeds {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.segera.sufeeds to javafx.fxml;
    exports com.segera.sufeeds;
}