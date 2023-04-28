package it.polimi.ingsw.client.view;

import it.polimi.ingsw.server.model.plank.CellPlank;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public interface View {
    JSONObject lobby();
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
    String catchAction(boolean myTurn);
    ArrayList<CellPlank> drag();
    int drop(int numCards) throws Exception;
    void showEndGame();
}
