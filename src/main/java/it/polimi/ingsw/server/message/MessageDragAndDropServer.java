package it.polimi.ingsw.server.message;

import com.google.gson.Gson;
import it.polimi.ingsw.server.ServerThread;
import it.polimi.ingsw.server.visitor.VisitorServer;
import org.json.simple.JSONObject;

public class MessageDragAndDropServer implements MessageServer{


    private ServerThread serverThread;
    private DropStructure dr;


    public MessageDragAndDropServer(ServerThread st, JSONObject obj) {
        this.dr = new DropStructure();
        this.dr = new Gson().fromJson((String) obj.get("data"), DropStructure.class);
        serverThread=st;
    }

    @Override
    public void accept(VisitorServer v) {
        v.visit(this);

    }

    public DropStructure getDr(){
        return dr;
    }


    public ServerThread getServerThread() {
        return serverThread;
    }
}
