package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import javafx.scene.control.TextField;

/**
 * This class manage the confirm button  in the join scene
 */
public class GUI_CreateController { //

    public static void onConfirmCreateClick(TextField userCreate, TextField nPlayers){
        Client client = GUIController.getClient();
        String username=userCreate.getText();
        int numPlayers=Integer.parseInt(nPlayers.getText());
        client.getModel().setMyName(username);
        client.getMessager().sendMessage(client.getMessager().getMessageHandler().sendCreateGame(numPlayers,username));
    }

}
