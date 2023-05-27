package it.polimi.ingsw.client.message;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.visitor.VisitorClient;

import java.io.IOException;

/**
 * This class creates to contain the message of a positive response of the server about a connection attempt
 */

public class MessageOKConnectionClient implements MessageClient { //
    private Client client;
    private int idGame;

    public MessageOKConnectionClient(Client client, int idGame) {
        this.client = client;
        this.idGame = idGame;
    }

    public Client getClient() {
        return client;
    }
    public int getIdGame() {
        return idGame;
    }

    @Override
    public void accept(VisitorClient visitor) throws IOException { //call the visitor
        visitor.visit(this);
    }
}
