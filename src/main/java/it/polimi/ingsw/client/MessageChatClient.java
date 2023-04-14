package it.polimi.ingsw.client;

import it.polimi.ingsw.client.chat.ClientChatReader;
import org.json.simple.JSONObject;

public class MessageChatClient implements MessageClient{

    private ClientChatReader chatReader;

    private String name;

    private String message;

    public MessageChatClient(ClientChatReader clr, JSONObject obj) {
        chatReader=clr;
        name=obj.get("name").toString();
        message=obj.get("message").toString();
    }

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }
}
