package it.polimi.ingsw.client.view;

public interface Controller {

    void startController();
    void showStateGame() throws Exception;
    void showEndGame();
    void showErrorMessage(String message);
    void showOkConnection(Integer idGame);
}
