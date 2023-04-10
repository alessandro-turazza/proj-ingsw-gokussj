package it.polimi.ingsw.server;

import it.polimi.ingsw.game_manager.GameManager;
import it.polimi.ingsw.stategame.StateGame;
import it.polimi.ingsw.user.User;
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

    public void sendInitConfig(GameManager gm, User user){
        JSONObject obj = new JSONObject();
        StateGame stateGame=new StateGame(gm);
        obj.put("response","new_turn");
        obj.put("active_user", user.getName());
        obj.put("plank", stateGame.messagePlank());
        out.println(obj.toJSONString());
    }
}
