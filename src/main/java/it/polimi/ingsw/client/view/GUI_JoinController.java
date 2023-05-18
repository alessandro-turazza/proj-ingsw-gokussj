package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import javafx.scene.control.TextField;

public class GUI_JoinController {
    public static void onConfirmEnterClick(TextField userEnter, TextField idEnter){
        Client client = GUIController.getClient();
        String username=userEnter.getText();
        Integer idGame=Integer.parseInt(idEnter.getText().toString());
        client.getModel().setMyName(username);
        client.getMessager().sendMessage(client.getMessager().getMessageHandler().sendJoinGame(idGame,username));
    }

}
