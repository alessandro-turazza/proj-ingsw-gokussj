package it.polimi.ingsw.client.message;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.visitor.VisitorClient;
import org.json.simple.JSONObject;

/**
 * THis class contains a chat's message
 */
public class MessageChat implements MessageClient{

    private Client client;

    private JSONObject obj;

    public MessageChat(Client client, JSONObject obj) {
        this.obj = obj;
        this.client = client;
    }

    @Override
    public void accept(VisitorClient visitor) throws Exception { //call the visitor
        visitor.visit(this);
    }


    public Client getClient() {
        return client;
    }

    public JSONObject getObj() {
        return obj;
    }
}
