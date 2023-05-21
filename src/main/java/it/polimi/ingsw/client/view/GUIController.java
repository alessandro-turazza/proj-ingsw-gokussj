package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.Client;
import org.json.simple.JSONObject;

import java.io.IOException;

import static javafx.application.Application.launch;

public class GUIController implements Controller{
    private GUI view;

    private static Client client;

    public GUIController(Client client) {
        GUIController.client = client;
    }


    @Override
    public void startController() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                launch(GUI.class);
            }
        }
        ).start();
    }

    @Override
    public void resetStart() throws IOException {
        GUI.showKoConnection();
    }

    @Override
    public void showStateGame() throws Exception {
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
        String message=jsonObject.get("namePlayer").toString() + ": " + jsonObject.get("message").toString();
        GUI.showChatMessage(message);
    }

    public static Client getClient(){
        return client;
    }

}
