package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GUI_JoinController {

    private Scene sceneLobby;
    private static Stage stage;
    private Client client;
    @FXML
    private TextField userEnter;
    @FXML
    private Spinner<Integer> idEnter;
    public static void setStage(Stage stage) {
        GUI_JoinController.stage = stage;
    }


    @FXML
    protected void onConfirmEnterClick(){
        stage = GUI.getStage();
        client = GUIController.getClient();
        String username=userEnter.getText();
        Integer idGame=idEnter.getValue();
        client.getMessager().sendMessage(client.getMessager().getMessageHandler().sendJoinGame(idGame,username));
    }

}
