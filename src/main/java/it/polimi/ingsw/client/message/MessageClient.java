package it.polimi.ingsw.client.message;

import it.polimi.ingsw.client.visitor.VisitorClient;

public interface MessageClient {

    void accept(VisitorClient visitor) throws Exception;

}
