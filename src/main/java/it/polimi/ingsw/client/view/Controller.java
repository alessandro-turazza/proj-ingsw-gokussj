package it.polimi.ingsw.client.view;

import java.io.IOException;

public interface Controller {

    void startController();
    void resetStart() throws IOException;
    void showStateGame() throws Exception;
    void showEndGame();
    void showErrorMessage(String message);
    void showOkConnection(Integer idGame) throws IOException;
}
