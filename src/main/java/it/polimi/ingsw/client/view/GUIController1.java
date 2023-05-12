package it.polimi.ingsw.client.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUIController1 {

    private Scene startScene, sceneLobby;
    private static Stage stage;

    public static void setStage(Stage stage) {
        GUIController1.stage = stage;
    }

    @FXML
    private Label welcomeText;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onCreateGameClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-2.fxml"));
        Pane root = fxmlLoader.load();
        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getBackgroundStart(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);
        root.setBackground(new Background(backgroundImage));
        sceneLobby = new Scene(root, 1920,1080);
        stage.setScene(sceneLobby);
    }

    @FXML
    protected void onJoinGame(){
        System.out.println("Join in game");
    }

}