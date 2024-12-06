package com.example.demogamebai2;

import com.example.demogamebai2.utilities.MediaManager;
import com.example.demogamebai2.utilities.SceneManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        MediaManager.getInstance().playBackgroundMusic("/MusicSource/BackgroundMusic/retro-gaming-271301.mp3",0.5);
        SceneManager.getInstance().setPrimaryStage(stage);
        SceneManager.getInstance().switchScene("/com/example/demogamebai2/fxmlfile/IntroGameScene.fxml", "/com/example/demogamebai2/stylefile/IntroGameStyle.css");


    }
    public static void main(String[] args) {
        launch();
    }
}