module com.example.demogamebai2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    // Mở gói cho JavaFX để tải FXML và sử dụng reflection
    opens com.example.demogamebai2 to javafx.fxml;

    opens com.example.demogamebai2.controller.menucontroller to javafx.fxml;
    // Xuất các gói nếu cần thiết
    exports com.example.demogamebai2;
    exports com.example.demogamebai2.model;


    exports com.example.demogamebai2.controller.menucontroller;
    exports com.example.demogamebai2.controller.gameplaycontroller.bacay;
    opens com.example.demogamebai2.controller.gameplaycontroller.bacay to javafx.fxml;
    exports com.example.demogamebai2.controller.gameplaycontroller.samloc;
    opens com.example.demogamebai2.controller.gameplaycontroller.samloc to javafx.fxml;
    exports com.example.demogamebai2.utilities;
    opens com.example.demogamebai2.utilities to javafx.fxml;


}
