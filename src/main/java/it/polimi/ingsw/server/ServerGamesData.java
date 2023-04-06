package it.polimi.ingsw.server;

import it.polimi.ingsw.game_manager.GameManager;
import it.polimi.ingsw.user.User;

import java.util.ArrayList;

public class ServerGamesData {
    private static ArrayList<GameManager> gamesList;

    public ServerGamesData(){
        gamesList = new ArrayList<>();
    }

    public synchronized static void createNewGame(User user, int numUser){
        gamesList.add(new GameManager(user, numUser, gamesList.size()));
    }

    public synchronized static boolean addPlayerToGame(User user, int codPartita){
        for(int i = 0; i < gamesList.size(); i++){
            if(gamesList.get(i).getIdGame() == codPartita && gamesList.get(i).getUsers().size() < gamesList.get(i).getNumUser()){
                gamesList.get(i).getUsers().add(user);
                return true;
            }
        }
        return false;
    }

    public synchronized static boolean fullGame(int idGame){
        for(GameManager gm: gamesList){
            if(gm.getIdGame() == idGame && gm.getUsers().size() == gm.getNumUser())
                return true;
        }
        return false;
    }
}
