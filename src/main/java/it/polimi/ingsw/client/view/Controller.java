package it.polimi.ingsw.client.view;

import org.json.simple.JSONObject;

import java.io.IOException;

public interface Controller {

    void startController();
    void resetStart() throws IOException;
    void showStateGame() throws Exception;
    void showEndGame() throws Exception;
    void showErrorMessage(String message);
    void showOkConnection(Integer idGame) throws IOException;

    void showChatMessage(JSONObject jsonObject);
}
