package it.polimi.ingsw.server;

import it.polimi.ingsw.server.model.game_manager.GameManager;
import it.polimi.ingsw.server.model.user.User;

import java.util.ArrayList;
/*This class contains all the attributes and methods in order to interface the model to the server side*/
public class ServerGame {
    private ArrayList<ServerThread> players;

    private GameManager gameManager;
    private int idGame;


    public ServerGame(ServerThread s, User user, int numPlayer, int id){
        this.players = new ArrayList<>();
        this.players.add(s);
        this.gameManager = new GameManager(user, numPlayer, id);
        this.idGame = id;
        System.out.println(user.getName() + " has just created game " + id);
    }


    public synchronized GameManager getGameManager() {
        return gameManager;
    }

    public synchronized ArrayList<ServerThread> getPlayers() {
        return players;
    }

    public synchronized int getIdGame() {
        return idGame;
    }

    public synchronized boolean addNewPlayer(ServerThread st, User user){
        boolean res = gameManager.addNewPlayer(user);

        if(res){
            players.add(st);
            System.out.println(user.getName() + " has just joined game " + idGame);

            if(gameManager.getNumUser() == players.size()){
                gameManager.startGame();
            }
        }

        return res;
    }

}
