package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GUI_CreateController {

    private Scene sceneLobby;
    private static Stage stage;
    private GUI view;
    private Client client;
    @FXML
    private TextField userCreate,numPlayersCreate;
    public static void setStage(Stage stage) {
        GUI_CreateController.stage = stage;

    }

    public void setView(GUI view) {
        this.view = view;
    }

    @FXML
    protected void onConfirmCreateClick(){
        client = GUIController.getClient();
        String username=userCreate.getText();
        Integer numPlayers=Integer.parseInt(numPlayersCreate.getText());
        client.getMessager().sendMessage(client.getMessager().getMessageHandler().sendCreateGame(numPlayers,username));
    }

}
