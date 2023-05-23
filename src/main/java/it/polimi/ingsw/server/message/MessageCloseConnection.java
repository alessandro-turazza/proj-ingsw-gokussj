package it.polimi.ingsw.server.message;

import it.polimi.ingsw.server.ServerThread;
import it.polimi.ingsw.server.visitor.VisitorServer;
/*Class that represent the message of the control connection in visitor pattern*/
public class MessageCloseConnection implements MessageServer{
    private ServerThread serverThread;

    public MessageCloseConnection(ServerThread serverThread) {
        this.serverThread = serverThread;
    }

    @Override
    public void accept(VisitorServer v) {
        v.visit(this);
    }

    public ServerThread getServerThread() {
        return serverThread;
    }
}
