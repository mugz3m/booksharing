module ru.george.booksharing.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.george.booksharing.client to javafx.fxml;
    exports ru.george.booksharing.client;
}