package it.polimi.ingsw.client.message;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.visitor.VisitorClient;

public class MessageOKConnectionClient implements MessageClient {
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
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }
}
