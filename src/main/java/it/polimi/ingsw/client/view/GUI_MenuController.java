package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GUI_MenuController {

    private Scene startScene, sceneLobby;
    private static Stage stage;
    private Client client;

    public static void setStage(Stage stage) {
        GUI_MenuController.stage = stage;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    @FXML
    protected void onCreateGameClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-create.fxml"));
        Pane root = fxmlLoader.load();
        GUI_CreateController controller=new GUI_CreateController();
        GUI_CreateController.setStage(stage);
        controller.setClient(client);
        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getBackgroundStart(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);
        root.setBackground(new Background(backgroundImage));
        sceneLobby = new Scene(root, 1000,600);
        stage.setScene(sceneLobby);

    }

    @FXML
    protected void onJoinGameClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI.class.getResource("scene-join.fxml"));
        Pane root = fxmlLoader.load();
        GUI_JoinController ctrl=new GUI_JoinController();
        GUI_JoinController.setStage(stage);
        ctrl.setClient(client);
        BackgroundSize size = new BackgroundSize(0,0,true,true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(PicturesLoad.getBackgroundStart(), NO_REPEAT, NO_REPEAT, BackgroundPosition.DEFAULT, size);
        root.setBackground(new Background(backgroundImage));
        sceneLobby = new Scene(root, 1000,600);
        stage.setScene(sceneLobby);

    }


    public Client getClient() {
        return client;
    }
}