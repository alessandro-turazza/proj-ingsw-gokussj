package it.polimi.ingsw.server;

import it.polimi.ingsw.game_manager.GameManager;
import it.polimi.ingsw.stategame.StateGame;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSender {
    private PrintWriter out;

    public ServerSender(Socket s) throws IOException {
        this.out = new PrintWriter(s.getOutputStream(), true);
    }
    public void sendOk(){
        JSONObject obj = new JSONObject();
        obj.put("response", "OK");
        out.println(obj.toJSONString());
    }

    public void sendKO(){
        JSONObject obj = new JSONObject();
        obj.put("response", "KO");
        out.println(obj.toJSONString());
    }


    public void sendStateGame(GameManager gm){
        JSONObject obj = new JSONObject();
        StateGame stateGame=new StateGame(gm);
        obj.put("response","new_turn");
        obj.put("state_game", stateGame.messageStateGame());
        out.println(obj.toJSONString());
    }

    public void sendEndOfGame(GameManager gm){
        JSONObject obj = new JSONObject();
        StateGame stateGame=new StateGame(gm);
        obj.put("response","end_game");
        obj.put("state_game", stateGame.messageStateGame());
        out.println(obj.toJSONString());
    }

    public void sendMessage(String name, String message){
        JSONObject obj = new JSONObject();
        obj.put("player_name", name);
        obj.put("message", message);
        out.println(obj.toJSONString());
    }
}
