package it.polimi.ingsw.client.model;

import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.server.model.common_goal.TokenCard;
import it.polimi.ingsw.server.state_game.CommonGoalClone;
import it.polimi.ingsw.server.state_game.StateGame;
import it.polimi.ingsw.server.model.plank.CellPlank;
import it.polimi.ingsw.server.model.plank.Plank;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.model.user.bookshelf.Bookshelf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class ClientModel {
    private int idGame;
    private ArrayList<User> players;
    private Plank plank;

    private String activeUser;

    private String myName;

    private ArrayList<CommonGoalClone> commonGoals;

    public ClientModel(){}
    public ClientModel(StateGame stateGame, String clientName, View view) {
        this.players = stateGame.getUsersClone();
        this.plank = stateGame.getPlankClone();
        this.activeUser = stateGame.getActiveUser();
        this.commonGoals = stateGame.getCommonGoalsClone();
        this.myName=clientName;
    }

    public boolean checkDrop(int numCard,int column) throws Exception {
        for(User user:players)if(user.getName().equals(myName))return user.getBookshelf().checkColumn(column) + 1 >= numCard;
        throw new Exception("Errore nickname non trovato");
    }

    public boolean checkDrag(ArrayList<CellPlank> chosenCard){
        ArrayList<Integer> rows= new ArrayList<>();
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

    public ArrayList<CellPlank> getAdjacentCell(CellPlank cellPlank) throws Exception {
        //if Adjacent cell is not playable return null
        ArrayList<CellPlank> result=new ArrayList<>();
        if(!(plank.getBoard()[cellPlank.getRow()][cellPlank.getColumn()].equals(cellPlank)))throw new Exception("Cella non valida");

        if((cellPlank.getRow()==plank.getDIM()-1))result.add(null);
        else{
            if(plank.getBoard()[cellPlank.getRow()+1][cellPlank.getColumn()]==null)result.add(null);
            else if(!(plank.getBoard()[cellPlank.getRow()+1][cellPlank.getColumn()].getPlayable()))result.add(null);
            else result.add(plank.getBoard()[cellPlank.getRow()+1][cellPlank.getColumn()]);
        }

        if((cellPlank.getColumn()==plank.getDIM()-1))result.add(null);
        else{
            if(plank.getBoard()[cellPlank.getRow()][cellPlank.getColumn()+1]==null)result.add(null);
            else if(!(plank.getBoard()[cellPlank.getRow()][cellPlank.getColumn()+1].getPlayable()))result.add(null);
            else result.add(plank.getBoard()[cellPlank.getRow()][cellPlank.getColumn()+1]);
        }

        if((cellPlank.getRow()==0))result.add(null);
        else{
            if(plank.getBoard()[cellPlank.getRow()-1][cellPlank.getColumn()]==null)result.add(null);
            else if(!(plank.getBoard()[cellPlank.getRow()-1][cellPlank.getColumn()].getPlayable()))result.add(null);
            else result.add(plank.getBoard()[cellPlank.getRow()-1][cellPlank.getColumn()]);
        }

        if((cellPlank.getColumn()==0))result.add(null);
        else{
            if(plank.getBoard()[cellPlank.getRow()][cellPlank.getColumn()-1]==null)result.add(null);
            else if(!(plank.getBoard()[cellPlank.getRow()][cellPlank.getColumn()-1].getPlayable()))result.add(null);
            else result.add(plank.getBoard()[cellPlank.getRow()][cellPlank.getColumn()-1]);
        }

        return result;
    }

    public Boolean[][] getPositionPlayablePlank(){
        Boolean[][] result=new Boolean[plank.getDIM()][plank.getDIM()];
        for(int rows=0;rows< plank.getDIM();rows++)
            for(int columns=0;columns< plank.getDIM();columns++){
                if(plank.getBoard()[rows][columns]!=null)result[rows][columns]= plank.getBoard()[rows][columns].getPlayable();
                else result[rows][columns]=false;
            }
        return result;
    }

    public ArrayList<String> getUsernames(){
        ArrayList<String> result=new ArrayList<>();
        for(User user:players)result.add(user.getName());
        return result;
    }

    public ArrayList<Integer> getPoints(){
        ArrayList<Integer> result=new ArrayList<>();
        for(User user:players)result.add(user.getPoints());
        return result;
    }

    public CellPlank getCellPlank(int row,int column){
        if(row >= 0 && row< plank.getDIM() && column>=0 && column< plank.getDIM())return plank.getBoard()[row][column];
        return null;
    }

    public Bookshelf getMyBookshelf() {
        for(User user:players)if(user.getName().equals(myName))return user.getBookshelf();
        return null;
    }

    public Bookshelf getBookshelf(String player) {
        for(User user:players)if(user.getName().equals(player))return user.getBookshelf();
        return null;
    }

    public TokenCard getLastTokenCardCommonGoal(int series) {
        //if commongoal is empty return null
        if(series>=1 && series<=commonGoals.size())return commonGoals.get(series-1).getLastTokenCard();
        return null;
    }

    public User getUserByName(String username){
        for(User user: players){
            if(user.getName().equals(username))
                return user;
        }
        return null;
    }

    public synchronized ArrayList<User> getPlayers() {
        return players;
    }

    public synchronized Plank getPlank() {
        return plank;
    }

    public synchronized String getActiveUser() {
        return activeUser;
    }
    public synchronized int getIdGame() {
        return idGame;
    }
    public synchronized String getMyName() {
        return myName;
    }
    public synchronized ArrayList<CommonGoalClone> getCommonGoals() {
        return commonGoals;
    }

    public synchronized void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public synchronized void setPlayers(ArrayList<User> players) {
        this.players = players;
    }

    public synchronized void setPlank(Plank plank) {
        this.plank = plank;
    }

    public synchronized void setActiveUser(String activeUser) {
        this.activeUser = activeUser;
    }

    public synchronized void setMyName(String myName) {
        this.myName = myName;
    }

    public synchronized void setCommonGoals(ArrayList<CommonGoalClone> commonGoals) {
        this.commonGoals = commonGoals;
    }

    public synchronized CommonGoalClone getCommonGoal(int series){
        if(series>=1 && series<=commonGoals.size())return commonGoals.get(series-1);
        return null;
    }
}
