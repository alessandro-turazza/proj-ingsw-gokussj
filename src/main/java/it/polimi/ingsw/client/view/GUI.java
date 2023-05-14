package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI extends Application{
    private static final int MAX_CELLS_DROP=3;
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;

    //private GUIController controller;
    private static Client client;
    private static Stage stage;
    private static Label waitLabel;
    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
            GUI.stage=stage;
            this.client = GUIController.getClient();
            PicturesLoad.loadImages();
            FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("startmenu.fxml"));
            Pane root = fxmlLoader.load();

            stage.setTitle("MyShelfie");

            BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
            BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getBackgroundStart(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);

            root.setBackground(new Background(backgroundImage));
            Scene startScene = new Scene(root, 1000,600);

            stage.setScene(startScene);
            stage.show();

    }

    public static Client getClient() {
        return client;
    }

    public static void showOkConnection( int idGame) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-wait.fxml"));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root, 1000, 600);
        Platform.runLater(()->{
                waitLabel = new Label();
                waitLabel.setText("Sei stato aggiunto correttamente alla partita " + idGame +".\nIn attesa deli altri giocatori...");
                waitLabel.setTextFill(Color.rgb(0,0,255));
                root.getChildren().add(waitLabel);
                stage.setScene(scene);

        });

    }

    public static void showStateGame(){
        Platform.runLater(()->{
            try {
                stage.setScene(GUI_TurnController.showStateGame());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
