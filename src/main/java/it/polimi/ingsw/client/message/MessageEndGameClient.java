package it.polimi.ingsw.client.message;

import com.google.gson.Gson;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.visitor.VisitorClient;
import it.polimi.ingsw.server.state_game.StateGame;
import org.json.simple.JSONObject;

/**
 * This class Created to contain the message of the end of the game
 */
public class MessageEndGameClient implements MessageClient { //
    private Client client;
    private StateGame stateGame;
    public MessageEndGameClient(Client client, JSONObject obj) {
        this.client = client;
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
