module com.example.crypto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.crypto to javafx.fxml;
    exports com.example.crypto;
}