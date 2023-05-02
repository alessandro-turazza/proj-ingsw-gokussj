package it.polimi.ingsw.server.model.game_manager;

import it.polimi.ingsw.server.model.common_goal.CommonGoal;
import it.polimi.ingsw.server.model.plank.CellPlank;
import it.polimi.ingsw.server.model.plank.Plank;
import it.polimi.ingsw.server.model.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


public class TurnManager {
    private TurnUser users;
    private Plank plank;


    private ArrayList<CommonGoal> commonGoals;

    public ArrayList<CommonGoal> getCommonGoals() {
        return commonGoals;
    }

    public TurnManager(ArrayList<User> users, Plank plank, ArrayList<CommonGoal> commonGoals) {
        for(User user:users)user.initializePointsToken(commonGoals.size());
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
        if(users.activeUser().getBookshelf().checkColumn(column)==null)return false;
        return users.activeUser().getBookshelf().checkColumn(column) + 1 >= numCard;
    }


    //public void activeTurnUser(){}
    public User updateGame(ArrayList<CellPlank> chosenCard, int column) throws Exception {
        if(!checkDrag(chosenCard))throw new Exception("Ripetere scelta, tessere non valide");
        if(!checkDrop(chosenCard.size(),column))throw new Exception("Ripetere scelta, troppe tessere");
        for(CellPlank cellPlank:chosenCard){
            users.activeUser().dropObjectCard(plank.dragObjectCard(cellPlank),column);
        }
        if(users.activeUser().getBookshelf().isFull())users.lastTurn();
        for(int index=0;index<commonGoals.size();index++){
            if(users.activeUser().getPointsToken(index)==0){
                if(commonGoals.get(index).checkRule(users.activeUser()))
                    users.activeUser().setPointsToken(commonGoals.get(index).getPoint(), index);
            }
        }
        plank.checkPlayable();
        if(plank.checkRefull())plank.fillPlank();
        return users.next();

    }
    public User updateGame(ArrayList<Integer> X,ArrayList<Integer> Y, int column) throws Exception {
        if(X.size()!=Y.size())throw new Exception("Posizioni non valide");
        ArrayList<CellPlank> cellPlanks=new ArrayList<>();
        for(int i=0;i< X.size();i++){
            if(plank.getBoard()[X.get(i)][Y.get(i)]==null)throw new Exception("Posizioni non valide");
            cellPlanks.add(plank.getBoard()[X.get(i)][Y.get(i)]);
        }
        return updateGame(cellPlanks,column);
    }

    public TurnUser getUsers() {
        return users;
    }

    public Plank getPlank() {
        return plank;
    }

}
