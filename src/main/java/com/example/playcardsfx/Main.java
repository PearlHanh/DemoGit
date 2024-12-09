package com.example.playcardsfx;

/*import com.example.playcardsfx.utilities.MediaManager;
import com.example.playcardsfx.utilities.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        MediaManager.getInstance().playBackgroundMusic("/MusicSource/BackgroundMusic/retro-gaming-271301.mp3",0.5);
        SceneManager.getInstance().setPrimaryStage(stage);
        SceneManager.getInstance().switchScene("/com/example/playcardsfx/fxmlfile/IntroGameScene.fxml", "/com/example/playcardsfx/stylefile/IntroGameStyle.css");


    }
    public static void main(String[] args) {
        launch();
    }
}

 */


//Test danh voi may
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/playcardsfx/fxmlfile/SamLocBotScene.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SamLocBot");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}