package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import javafx.scene.control.TextField;

/**
 * This class manages the confirm button  in the join scene
 */
public class GUI_JoinController {
    public static void onConfirmEnterClick(TextField userEnter, TextField idEnter){
        Client client = GUIController.getClient();
        String username=userEnter.getText();
        int idGame=Integer.parseInt(idEnter.getText());
        client.getModel().setMyName(username);
        client.getMessager().sendMessage(client.getMessager().getMessageHandler().sendJoinGame(idGame,username));
    }

}
