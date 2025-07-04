package it.polimi.ingsw.client.view;

import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * This is the inteface for controlling cli and gui view application
 */

public interface Controller {

    void startController();
    void resetStart() throws IOException;
    void showStateGame() throws Exception;
    void showEndGame() throws Exception;
    void showErrorMessage(String message);
    void showLightErrorMessage(String message);
    void showOkConnection(Integer idGame) throws IOException;
    void showChatMessage(JSONObject jsonObject);
}
