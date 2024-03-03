module org.example {
    requires javafx.controls;
    requires com.jfoenix;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    requires org.json;
    requires javafx.media;
    //requires java;
    // other module requirements
    exports Models;
    opens Controllers  to javafx.fxml;
    exports Controllers;
    exports Services;
    opens Services to javafx.fxml;
}