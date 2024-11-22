module org.example.pharmacymanagmentfrontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.pharmacymanagmentfrontend to javafx.fxml;
    exports org.example.pharmacymanagmentfrontend;
}