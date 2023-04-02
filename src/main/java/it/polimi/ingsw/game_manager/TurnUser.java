package it.polimi.ingsw.game_manager;

import it.polimi.ingsw.user.User;

import java.util.ArrayList;

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
        lastTurn=true;
    }
}
