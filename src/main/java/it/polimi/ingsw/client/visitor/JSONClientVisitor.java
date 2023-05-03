package it.polimi.ingsw.client.visitor;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.message.*;

import java.io.IOException;

public class JSONClientVisitor implements VisitorClient {


    @Override
    public void visit(MessageGameStateClient element) {

    }

    @Override
    public void visit(MessageNewTurnClient element) throws Exception {
        Client client = element.getClient();
        client.getModel().setPlayers(element.getStateGame().getUsersClone());
        client.getModel().setPlank(element.getStateGame().getPlankClone());
        client.getModel().setCommonGoals(element.getStateGame().getCommonGoalsClone());
        client.getModel().setActiveUser(element.getStateGame().getActiveUser());
        client.getViewController().getView().showStateGame();
        client.handleTurn();
    }

    @Override
    public void visit(MessageOKConnectionClient element) {
        Client client = element.getClient();
        client.getModel().setIdGame(element.getIdGame());
        client.getViewController().getView().showCorrectMessage("Sei stato aggiunto correttamente alla partita " + element.getIdGame());
        client.getViewController().getView().showNormalMessage("In attesa degli altri giocatori...");
        client.getViewController().getView().showNormalMessage("----------------------------");
    }

    @Override
    public void visit(MessageKOConnectionClient element) {
        if(element.getObject().equals("NOTEX"))
            element.getClient().getViewController().getView().showErrorMessage("Errore, partita inesistente");
        else if(element.getObject().equals("USER/FULL"))
            element.getClient().getViewController().getView().showErrorMessage("Errore, username già in uso / partita piena");
        element.getClient().getViewController().setClientDatas();
    }

    @Override
    public void visit(MessageChat element) {
        element.getClient().getChat().chatAdd(element.getObj());
    }

    @Override
    public void visit(MessageEndGameClient element) throws Exception {
        Client client = element.getClient();
        client.getModel().setPlayers(element.getStateGame().getUsersClone());
        client.getModel().setPlank(element.getStateGame().getPlankClone());
        client.getModel().setCommonGoals(element.getStateGame().getCommonGoalsClone());
        client.getModel().setActiveUser(element.getStateGame().getActiveUser());
        client.getViewController().getView().showStateGame();
        client.getViewController().getView().showEndGame();
        client.getMessager().sendMessage(client.getMessager().getMessageHandler().sendCloseConnection());
    }
}
