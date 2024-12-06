package com.example.demogamebai2.controller.menucontroller;

import com.example.demogamebai2.utilities.MediaManager;
import com.example.demogamebai2.utilities.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
/*
Thằn này quản lí màn hình chính
 */
public class StartMenuController {
    @FXML
    Button startButton;

    public void gameSelect(ActionEvent event) throws IOException {
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/mouse-click-sound-233951.mp3", 1);
        SceneManager.getInstance().switchScene("/com/example/demogamebai2/fxmlfile/SelectGameSence.fxml", "/com/example/demogamebai2/stylefile/Style.css");

    }
}
