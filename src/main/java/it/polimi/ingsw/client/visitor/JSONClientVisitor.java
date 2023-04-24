package it.polimi.ingsw.client.visitor;

import it.polimi.ingsw.client.message.*;

public class JSONClientVisitor implements VisitorClient {


    @Override
    public void visit(MessageGameStateClient element) {

    }

    @Override
    public void visit(MessageNewTurnClient element) {

    }

    @Override
    public void visit(MessageOKConnectionClient element) {
        System.out.println("Connessione effettuata");
    }

    @Override
    public void visit(MessageKOConnectionClient element) {
        System.out.println("Connessione NON riuscita");
    }
    @Override
    public void visit(MessageEndGameClient element) {

    }
}
