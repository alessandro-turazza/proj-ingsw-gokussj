package it.polimi.ingsw.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.stategame.StateGame;
import org.json.simple.JSONObject;

import java.lang.reflect.Type;

public class MessageNewTurnClient implements MessageClient {

    private StateGame stateGame;
    private Client client;

    public MessageNewTurnClient(Client cl, JSONObject obj) {
        client=cl;
        Type Type = new TypeToken<StateGame>() {}.getType();
        Gson gobj= new Gson();
        stateGame=gobj.fromJson(obj.get("state_game").toString(),Type);
    }
    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }


    public StateGame getStateGame() {
        return stateGame;
    }
}
