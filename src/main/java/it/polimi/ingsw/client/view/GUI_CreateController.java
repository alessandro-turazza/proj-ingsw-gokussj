package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import javafx.scene.control.TextField;

public class GUI_CreateController {

    public static void onConfirmCreateClick(TextField userCreate, TextField nPlayers){
        Client client = GUIController.getClient();
        String username=userCreate.getText();
        Integer numPlayers=Integer.parseInt(nPlayers.getText().toString());
        client.getMessager().sendMessage(client.getMessager().getMessageHandler().sendCreateGame(numPlayers,username));
    }

}
