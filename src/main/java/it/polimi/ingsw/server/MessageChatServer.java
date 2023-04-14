package it.polimi.ingsw.server;

import it.polimi.ingsw.server.chat.ServerChatReader;
import org.json.simple.JSONObject;

public class MessageChatServer implements MessageServer{

    private String playerName;

    private String message;

    private ServerChatReader reader;
    public MessageChatServer(ServerChatReader rd, JSONObject obj){
        playerName=obj.get("player_name").toString();
        message=obj.get("message").toString();
        reader=rd;
    }
    @Override
    public void accept(VisitorServer v) {
        v.visit(this);
    }

    public ServerChatReader getReader() {
        return reader;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getMessage() {
        return message;
    }
}
