package it.polimi.ingsw.game_manager;

import it.polimi.ingsw.common_goal.CommonGoal;
import it.polimi.ingsw.plank.CellPlank;
import it.polimi.ingsw.plank.Plank;
import it.polimi.ingsw.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


public class TurnManager {
    private TurnUser users;
    private Plank plank;

    private ArrayList<CommonGoal> commonGoals;
    public TurnManager(ArrayList<User> users,Plank plank, ArrayList<CommonGoal> commonGoals) {
        this.users = new TurnUser(users);
        this.plank=plank;
        this.commonGoals=commonGoals;
    }
    public boolean checkDrag(ArrayList<CellPlank> chosenCard){
        ArrayList<Integer> rows=new ArrayList<>();
        ArrayList<Integer> columns=new ArrayList<>();
        for(CellPlank cellPlank:chosenCard){
            rows.add(cellPlank.getRow());
            columns.add(cellPlank.getColumn());
            if(!cellPlank.getPlayable())return false;
        }
        boolean validRow=true;
        boolean validColumn=true;
        for(int i=0;i<rows.size()-1;i++){
            if(!Objects.equals(rows.get(i), rows.get(i + 1)))validRow=false;
            if(!Objects.equals(columns.get(i), columns.get(i + 1)))validColumn=false;
        }
        if(!validRow && !validColumn)return false;
        if(validRow){
            Collections.sort(columns);
            for(int i=0;i<columns.size()-1;i++){
                if(columns.get(i+1)!=(columns.get(i)+1))return false;
            }
        }
        if(validColumn){
            Collections.sort(rows);
            for(int i=0;i<rows.size()-1;i++){
                if(rows.get(i+1)!=(rows.get(i)+1))return false;
            }
        }
        return true;
    }

    public boolean checkDrop(int numCard,int column){
        return users.activeUser().getBookshelf().checkColumn(column) + 1 >= numCard;
    }


    //public void activeTurnUser(){}
    public User updateGame(ArrayList<CellPlank> chosenCard, int column) throws Exception {
        if(!checkDrag(chosenCard))throw new Exception("Ripetere scelta, tessere non valide");
        if(!checkDrop(chosenCard.size(),column))throw new Exception("Ripetere scelta, troppe tessere");
        for(CellPlank cellPlank:chosenCard){
            plank.dragObjectCard(cellPlank.getRow(), cellPlank.getColumn());
            users.activeUser().dropObjectCard(cellPlank.getObjectCard(),column);

        }
        if(users.activeUser().getBookshelf().isFull())users.lastTurn();
        for(CommonGoal commonGoal: commonGoals){
            if(commonGoal.checkRule(users.activeUser()))users.activeUser().setPoints(users.activeUser().getPoints()+commonGoal.getPoint());
        }
        plank.checkPlayable();
        if(plank.checkRefull())plank.fillPlank();
        return users.next();

    }

    public TurnUser getUsers() {
        return users;
    }

    public Plank getPlank() {
        return plank;
    }
}
