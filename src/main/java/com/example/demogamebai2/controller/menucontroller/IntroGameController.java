package com.example.demogamebai2.controller.menucontroller;

import com.example.demogamebai2.utilities.MediaManager;
import com.example.demogamebai2.utilities.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class IntroGameController implements Initializable {
    @FXML
    StackPane stackPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void evenClickedScene(MouseEvent event){
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/click-menu-app-147357.mp3", 1);
        SceneManager.getInstance().switchScene(
                "/com/example/demogamebai2/fxmlfile/GameLoadingScene.fxml",
                "/com/example/demogamebai2/stylefile/Style.css");

    }


}
