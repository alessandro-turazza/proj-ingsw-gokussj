package it.polimi.ingsw.client.message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.visitor.VisitorClient;
import it.polimi.ingsw.server.StateGame;
import org.json.simple.JSONObject;

import java.lang.reflect.Type;

public class MessageNewTurnClient implements MessageClient {

    private StateGame stateGame;
    private Client client;

    public MessageNewTurnClient(Client cl, JSONObject obj) {
        client=cl;
        Type Type = new TypeToken<StateGame>() {}.getType();
        //Gson gobj= new Gson();
        //
        //stateGame = gobj.fromJson(obj.get("state_game"), Type);
        String s = obj.get("state_game").toString();
        Gson g = new Gson().newBuilder().setPrettyPrinting().create();

        System.out.println(g.toJson(s));

        stateGame = new Gson().fromJson(g.toJson(s), StateGame.class);
        System.out.println(new Gson().toJson(stateGame));
        //System.out.println(stateGame.messageStateGame());
        //String jsonString = obj.getOrDefault("state_game",null).toString();
        //stateGame= gobj.fromJson(jsonString, Type);
    }

    public Client getClient() {
        return client;
    }
    public StateGame getStateGame() {
        return stateGame;
    }
    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }



}
