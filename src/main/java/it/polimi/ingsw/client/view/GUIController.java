package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import org.json.simple.JSONObject;

import java.io.IOException;

import static javafx.application.Application.launch;

/**
 * This class responds to the external command by calling the GUI methods
 */
public class GUIController implements Controller{

    private static Client client;

    public GUIController(Client client) {
        GUIController.client = client;
    }


    @Override
    public void startController() {

        new Thread(() -> launch(GUI.class)
        ).start();
    }

    @Override
    public void resetStart() throws IOException {
        GUI.showKoConnection();
    }

    @Override
    public void showStateGame() {
        GUI.showStateGame();
    }

    @Override
    public void showEndGame() {
        GUI.showEndGame();
    }

    @Override
    public void showErrorMessage(String message) {
        GUI.showErrorMessage(message);
    }

    @Override
    public void showLightErrorMessage(String message) {
        GUI.showLightErrorMessage(message);
    }

    @Override
    public void showOkConnection(Integer idGame) throws IOException {
        GUI.showOkConnection(idGame);
    }

    @Override
    public void showChatMessage(JSONObject jsonObject) {
        String namePlayer=jsonObject.get("namePlayer").toString();
        String message= jsonObject.get("message").toString();
        GUI.showChatMessage(namePlayer,message);
    }

    public static Client getClient(){
        return client;
    }

}
