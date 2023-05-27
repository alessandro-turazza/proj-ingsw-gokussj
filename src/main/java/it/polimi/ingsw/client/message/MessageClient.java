package it.polimi.ingsw.client.message;

import it.polimi.ingsw.client.visitor.VisitorClient;

/**
 * This interface for messages in client for Visitor pattern
 */
public interface MessageClient {

    void accept(VisitorClient visitor) throws Exception;

}
