package it.polimi.ingsw.client.visitor;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ConnectionDeamon;
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
        client.getViewController().showStateGame();
    }

    @Override
    public void visit(MessageOKConnectionClient element) throws IOException {
        //System.out.println("in visitor");
        Client client = element.getClient();
        client.getModel().setIdGame(element.getIdGame());
        client.getViewController().showOkConnection(element.getIdGame());
        ConnectionDeamon deamon = new ConnectionDeamon();
        deamon.setIpServer(element.getClient().getMessager().getIpServer());
        deamon.start();
    }

    @Override
    public void visit(MessageKOConnectionClient element) throws IOException {
        if(element.getObject().equals("NOTEX"))
            element.getClient().getViewController().showErrorMessage("Errore, partita inesistente");
        else if(element.getObject().equals("USER/FULL"))
            element.getClient().getViewController().showErrorMessage("Errore, username già in uso / partita piena");
        element.getClient().getViewController().resetStart();
    }

    @Override
    public void visit(MessageChat element) {
        element.getClient().getChat().chatAdd(element.getObj());
    }

    @Override
    public void visit(MessageDisconnection element) {
        element.getClient().getViewController().showErrorMessage("L'utente " + element.getObj().get("user").toString() + " si è disconnesso.\nPartita terminata.");
    }

    @Override
    public void visit(MessageEndGameClient element) throws Exception {
        Client client = element.getClient();
        client.getModel().setPlayers(element.getStateGame().getUsersClone());
        client.getModel().setPlank(element.getStateGame().getPlankClone());
        client.getModel().setCommonGoals(element.getStateGame().getCommonGoalsClone());
        client.getModel().setActiveUser(element.getStateGame().getActiveUser());
        //client.getViewController().showStateGame();
        client.getViewController().showEndGame();
        client.getMessager().sendMessage(client.getMessager().getMessageHandler().sendCloseConnection());
    }
}
