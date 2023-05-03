package it.polimi.ingsw.server.message;

import it.polimi.ingsw.server.ServerThread;
import it.polimi.ingsw.server.visitor.VisitorServer;
import org.json.simple.JSONObject;

public class MessageChatServer implements MessageServer{

    private ServerThread serverThread;
    private JSONObject obj;

    public MessageChatServer(JSONObject obj, ServerThread serverThread) {
        this.serverThread=serverThread;
        this.obj = obj;
    }

    @Override
    public void accept(VisitorServer v) {
        v.visit(this);
    }

    public ServerThread getServerThread() {
        return serverThread;
    }

    public JSONObject getObj() {
        return obj;
    }
}
