package it.polimi.ingsw.server.model.game_manager;

import it.polimi.ingsw.server.model.user.User;

import java.util.ArrayList;

/*This class implements the methods to establish the active player of next turn with an iterator*/

public class TurnUser {
    private ArrayList<User> users;
    private boolean lastTurn;
    private int pos;

    public TurnUser(ArrayList<User> users) {
        //first user is in 0 pos
        this.users = users;
        this.pos = 0;
        this.lastTurn=false;
    }

    public User next() {
        if(pos==users.size()-1 && lastTurn)return null;
        if(pos==users.size()-1 && !lastTurn)pos=0;
        else pos++;
        return users.get(pos);
    }

    public User activeUser(){
        return users.get(pos);
    }

    public void lastTurn(){
        if(!lastTurn){
            lastTurn=true;
            activeUser().getPointsToken().add(1);
        }

    }

    public boolean isLastTurn() {
        return lastTurn;
    }
}
