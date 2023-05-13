package it.polimi.ingsw.client.message;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.visitor.VisitorClient;
import org.json.simple.JSONObject;

public class MessageDisconnection implements MessageClient{

    private JSONObject obj;

    private Client client;

    public MessageDisconnection(JSONObject obj, Client client) {
        this.obj = obj;
        this.client = client;
    }

    @Override
    public void accept(VisitorClient visitor) throws Exception {
        visitor.visit(this);
    }

    public JSONObject getObj() {
        return obj;
    }

    public Client getClient() {
        return client;
    }
}
