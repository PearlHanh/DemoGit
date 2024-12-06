package com.example.playcardsfx.controller.menucontroller;

import com.example.playcardsfx.utilities.MediaManager;
import com.example.playcardsfx.utilities.SceneManager;
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
                "/com/example/playcardsfx/fxmlfile/GameLoadingScene.fxml",
                "/com/example/playcardsfx/stylefile/Style.css");

    }


}
