package it.polimi.ingsw.client.view;

import it.polimi.ingsw.server.model.plank.CellPlank;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public interface View {
    public JSONObject lobby();
    public void showMessage(String message);
    public void showPlank();
    public void showBookshelf();
    public void showPersonalGoal();
    public void showCommonGoal();
    public ArrayList<CellPlank> drag();
    public int drop();
    public void showEndGame();
}
