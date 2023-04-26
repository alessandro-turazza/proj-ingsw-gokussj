package it.polimi.ingsw.client.view;

import it.polimi.ingsw.server.model.plank.CellPlank;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public interface View {
    JSONObject lobby();
    void showNormalMessage(String message);
    void showCorrectMessage(String message);
    void showErrorMessage(String message);
    void showPlank();
    void showBookshelf();
    void showPersonalGoal();
    void showCommonGoal();
    ArrayList<CellPlank> drag();
    int drop();
    void showEndGame();
}
