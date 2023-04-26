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
        element.getClient().getViewController().getView().showCorrectMessage("Sei stato aggiunto correttamente alla partita " + element.getIdGame());
        element.getClient().getViewController().getView().showNormalMessage("In attesa degli altri giocatori...");
    }

    @Override
    public void visit(MessageKOConnectionClient element) {
        if(element.getObject().equals("NOTEX"))
            element.getClient().getViewController().getView().showErrorMessage("Errore, partita inesistente");
        else if(element.getObject().equals("USER/FULL"))
            element.getClient().getViewController().getView().showErrorMessage("Errore, username già in uso / partita piena");
        element.getClient().startConnection();
    }
    @Override
    public void visit(MessageEndGameClient element) {

    }
}
