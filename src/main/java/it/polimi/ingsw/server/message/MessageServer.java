package it.polimi.ingsw.server.message;

import it.polimi.ingsw.server.visitor.VisitorServer;

public interface MessageServer {
    void accept(VisitorServer v);
}
