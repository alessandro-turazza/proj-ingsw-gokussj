package it.polimi.ingsw.client.view;

import it.polimi.ingsw.client.chat.Chat;
import it.polimi.ingsw.server.model.plank.CellPlank;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class GUI implements View {

    @Override
    public Character selectTypeGame() {
        return null;
    }

    @Override
    public JSONObject lobby(Character choose) {
        return null;
    }

    @Override
    public void showNormalMessage(String message) {

    }

    @Override
    public void showCorrectMessage(String message) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showStateGame() throws Exception {

    }

    @Override
    public void showPlank() {

    }

    @Override
    public void showBookshelfs() {

    }

    @Override
    public void showBookshelf(String username) throws Exception {

    }

    @Override
    public void showPersonalGoal() {

    }

    @Override
    public void showCommonGoals() {

    }

    @Override
    public void showUsers() {

    }

    @Override
    public void showCommonGoal(int idCommonGoal) {

    }

    @Override
    public String catchAction() {
        return null;
    }

    @Override
    public ArrayList<CellPlank> drag() {
        return null;
    }

    @Override
    public int drop(int numCards) throws Exception {
        return 0;
    }

    @Override
    public ArrayList<CellPlank> reorderCards(ArrayList<CellPlank> cells) {
        return null;
    }

    @Override
    public void showEndGame() {

    }

    @Override
    public void openChat(Chat chat) {

    }
}
