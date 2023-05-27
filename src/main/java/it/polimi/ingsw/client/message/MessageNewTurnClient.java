package it.polimi.ingsw.client.message;

import com.google.gson.Gson;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.visitor.VisitorClient;
import it.polimi.ingsw.server.state_game.StateGame;
import org.json.simple.JSONObject;

/**
 * This class Creates to contain the message of the start of a new turn, which contain the state of the game
 */
public class MessageNewTurnClient implements MessageClient { //Create to contain the message of the start of a new turn, which contain the state of the game

    private StateGame stateGame;
    private Client client;

    public MessageNewTurnClient(Client cl, JSONObject obj) {
        client=cl;
        String s = obj.get("state_game").toString();
        stateGame = new Gson().fromJson(s, StateGame.class);
    }

    public Client getClient() {
        return client;
    }
    public StateGame getStateGame() {
        return stateGame;
    }
    @Override
    public void accept(VisitorClient visitor) throws Exception { //call the visitor
        visitor.visit(this);
    }



}
