package it.polimi.ingsw.game_manager;

import it.polimi.ingsw.common_goal.CommonGoal;
import it.polimi.ingsw.plank.CellPlank;
import it.polimi.ingsw.plank.Plank;
import it.polimi.ingsw.user.User;

import java.util.ArrayList;

public class TurnManager {
    private TurnUser users;
    private Plank plank;

    private ArrayList<CommonGoal> commonGoals;
    public TurnManager(ArrayList<User> users,Plank plank, ArrayList<CommonGoal> commonGoals) {
        this.users = new TurnUser(users);
        this.plank=new Plank();
        this.commonGoals=commonGoals;
    }

    //public void activeTurnUser(){}
    public void startTurn(ArrayList<CellPlank> chosenCard, int column) throws Exception {
        for(CellPlank cellPlank:chosenCard){
            plank.dragObjectCard(cellPlank.getRow(), cellPlank.getColumn());
            users.activeUser().dropObjectCard(cellPlank.getObjectCard(),column);

        }
        for(CommonGoal commonGoal: commonGoals){
            if(commonGoal.checkRule(users.activeUser()))users.activeUser().setPoints(users.activeUser().getPoints()+commonGoal.getTokenCard().getPoints());
        }
        plank.checkPlayable();
        if(plank.checkRefull())plank.fillPlank();
        users.next();
        //notificaqualcuno??
    }

}
