package it.polimi.ingsw.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.game_manager.GameManager;
import it.polimi.ingsw.user.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class JSONServerVisitor implements VisitorServer{

    @Override
    public void visitMessageNewGame(MessageStartGameServer m) {
        int idGame=Server.insertNewGame(m.getServerThread(), m.getUser(), m.getNumPlayer());
        m.getServerThread().setIdGame(idGame);
        m.getServerThread().getSs().sendOk();
        m.getServerThread().setUser(m.getUser());
    }
    @Override
    public void visitMessageAddPlayer(MessageEnterInGame m) {
        boolean res = Server.getServerGameFromId(m.getIdGame()).addNewPlayer(m.getServerThread(), m.getUser());

        if(res){
            m.getServerThread().setIdGame(m.getIdGame());
            m.getServerThread().getSs().sendOk();
            m.getServerThread().setUser(m.getUser());
        }else
            m.getServerThread().getSs().sendKO();
    }


    public  void visitMessageDragAndDrop(MessageDragAndDropServer m){
        ServerGame serverGame=Server.getServerGameFromId(m.getServerThread().getIdGame());
        GameManager gm=serverGame.getGameManager();
        User user=null;
        User activeUser=gm.getTurnManager().getUsers().activeUser();

        try{
            //Chiama UpdateGame passando la drag e la drop generate dai suoi attributi.
            user=gm.getTurnManager().updateGame(m.getX_coordinate(),m.getY_coordinate(),m.getColumn());
        }
        catch (Exception e){
            //gestisce anche il caso di eccezioni(nel qual caso manda una KO al Client)
            m.getServerThread().getSs().sendKO();
        }
        m.getServerThread().getSs().sendOk();
        if(gm.getTurnManager().getMessageLastTurn()==1){
            serverGame.lastTurnNotify(activeUser);
            gm.getTurnManager().setMessageLastTurn(-1);
        }
        if(user==null){
            gm.endGame(); //gestisce ovviamente anche il caso di return=null
            serverGame.endGame();
        }
        else serverGame.updateStateGame();//prepara il ServerThread per inviare i dati aggiornati
    }
}
