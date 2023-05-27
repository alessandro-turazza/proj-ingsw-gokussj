package it.polimi.ingsw.server.message;

import it.polimi.ingsw.server.visitor.VisitorServer;

/**
 * This is Interface for Visitor pattern
 */

public interface MessageServer {
    void accept(VisitorServer v);
}
