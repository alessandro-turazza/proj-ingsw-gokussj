package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.chat.Chat;
import it.polimi.ingsw.server.model.plank.CellPlank;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public interface View {
    Character selectTypeGame();
    JSONObject lobby(Character choose);

    void showNormalMessage(String message);
    void showCorrectMessage(String message);
    void showErrorMessage(String message);
    void showStateGame() throws Exception;
    void showPlank();
    void showBookshelfs();
    void showBookshelf(String username) throws Exception;
    void showPersonalGoal();
    void showCommonGoals();
    void showUsers();
    void showCommonGoal(int idCommonGoal);
    String catchAction();
    ArrayList<CellPlank> drag();
    int drop(int numCards) throws Exception;
    ArrayList<CellPlank> reorderCards(ArrayList<CellPlank> cells);
    void showEndGame();
    void openChat(Chat chat);
}
