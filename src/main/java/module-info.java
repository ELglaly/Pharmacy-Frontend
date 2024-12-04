module org.example.pharmacymanagmentfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens org.example.pharmacymanagmentfrontend to javafx.fxml;
    exports org.example.pharmacymanagmentfrontend;
    exports org.example.pharmacymanagmentfrontend.Model;
    opens org.example.pharmacymanagmentfrontend.Model to javafx.fxml;
    opens org.example.pharmacymanagmentfrontend.Controller to javafx.fxml;
    exports org.example.pharmacymanagmentfrontend.Controller;
}
