package it.polimi.ingsw.client.message;

import it.polimi.ingsw.client.visitor.VisitorClient;

public class MessageOKConnectionClient implements MessageClient {
    private int idGame;

    public MessageOKConnectionClient(int idGame) {
        this.idGame = idGame;
    }

    public int getIdGame() {
        return idGame;
    }

    @Override
    public void accept(VisitorClient visitor) {
        visitor.visit(this);
    }
}
