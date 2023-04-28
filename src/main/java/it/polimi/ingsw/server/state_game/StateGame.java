package it.polimi.ingsw.server.state_game;

import com.google.gson.Gson;
import it.polimi.ingsw.server.model.common_goal.CommonGoal;
import it.polimi.ingsw.server.model.game_manager.GameManager;
import it.polimi.ingsw.server.model.plank.Plank;
import it.polimi.ingsw.server.model.user.User;

import java.util.ArrayList;

public class StateGame {
    private ArrayList<User> usersClone;
    private Plank plankClone;

    private String activeUser;

    private ArrayList<CommonGoalClone> commonGoalsClone;



    public StateGame(GameManager gameManager) {
        usersClone=new ArrayList<>();
        for(User user: gameManager.getUsers()){
            usersClone.add(user.getUserClone());
        }

        activeUser=gameManager.getTurnManager().getUsers().activeUser().getName();

        plankClone=gameManager.getPlank().getPlankClone();

        commonGoalsClone=new ArrayList<>();
        for(CommonGoal commonGoal:gameManager.getCommonGoals())
            commonGoalsClone.add(new CommonGoalClone(commonGoal));

    }

    public String messageStateGame(){
        return new Gson().toJson(this);
    }

    public ArrayList<User> getUsersClone() {
        return usersClone;
    }

    public Plank getPlankClone() {
        return plankClone;
    }

    public ArrayList<CommonGoalClone> getCommonGoalsClone() {
        return commonGoalsClone;
    }

    public String getActiveUser() {
        return activeUser;
    }
}
