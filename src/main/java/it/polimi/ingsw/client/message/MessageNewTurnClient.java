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
        Gson gobj= new Gson();
        String jsonString = obj.getOrDefault("state_game",null).toString();
        stateGame= gobj.fromJson(jsonString, Type);
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
