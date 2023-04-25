package it.polimi.ingsw.client.view;

import it.polimi.ingsw.server.model.plank.CellPlank;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public interface View {
    void showErrorServer();
    JSONObject lobby();
    void showMessage(String message);
    void showPlank();
    void showBookshelf();
    void showPersonalGoal();
    void showCommonGoal();
    ArrayList<CellPlank> drag();
    int drop();
    void showEndGame();
}
