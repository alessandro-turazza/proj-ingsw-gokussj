package it.polimi.ingsw.client.visitor;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ConnectionDeamon;
import it.polimi.ingsw.client.message.*;

import java.io.IOException;

/**
 * This class implements all the methods and manage the operations to react to every message recived by the server for visitor pattern
 */
public class JSONClientVisitor implements VisitorClient {


    /**
     * This method manage the messages for the new turn
     */
    @Override
    public void visit(MessageNewTurnClient element) throws Exception {
        Client client = element.getClient();
        client.getModel().setPlayers(element.getStateGame().getUsersClone());
        client.getModel().setPlank(element.getStateGame().getPlankClone());
        client.getModel().setCommonGoals(element.getStateGame().getCommonGoalsClone());
        client.getModel().setActiveUser(element.getStateGame().getActiveUser());
        client.getViewController().showStateGame();
        client.getModel().setLastTurn(element.getStateGame().isLastTurn());
    }

    /**
     * This method manage the messages for checking the connection
     */
    @Override
    public void visit(MessageOKConnectionClient element) throws IOException {
        Client client = element.getClient();
        client.getModel().setIdGame(element.getIdGame());
        client.getViewController().showOkConnection(element.getIdGame());
        ConnectionDeamon deamon = new ConnectionDeamon();
        ConnectionDeamon.setIpServer(element.getClient().getMessager().getIpServer());
        deamon.start();
    }
    /**
     * This method manage the message that a server can't insert a user into a specific game
     */
    @Override
    public void visit(MessageKOConnectionClient element) throws IOException {
        if(element.getObject().equals("NOTEX"))
            element.getClient().getViewController().showErrorMessage("Errore, partita inesistente");
        else if(element.getObject().equals("USER/FULL"))
            element.getClient().getViewController().showErrorMessage("Errore, username già in uso / partita piena");
        element.getClient().getViewController().resetStart();

    }
    /**
     * This method manage the messages for the chat
     */
    @Override
    public void visit(MessageChat element) {
        element.getClient().getChat().chatAdd(element.getObj());
    }
    /**
     * This method manage the messages for the disconnection of another client in game
     */
    @Override
    public void visit(MessageDisconnection element) {
        element.getClient().getViewController().showErrorMessage("L'utente " + element.getObj().get("user").toString() + " si è disconnesso.\nPartita terminata.");

    }
    /**
     * This method manage the messages that the server can't make the drag and drop into his model correctly
     */
    @Override
    public void visit(MessageKODedClient element) {
        element.getClient().getViewController().showLightErrorMessage("Errore server, mossa rifiutata");
    }
    /**
     * This method manage the messages for the end game
     */
    @Override
    public void visit(MessageEndGameClient element) throws Exception {
        Client client = element.getClient();
        client.getModel().setPlayers(element.getStateGame().getUsersClone());
        client.getModel().setPlank(element.getStateGame().getPlankClone());
        client.getModel().setCommonGoals(element.getStateGame().getCommonGoalsClone());
        client.getModel().setActiveUser(element.getStateGame().getActiveUser());
        client.getViewController().showEndGame();
        client.getMessager().sendMessage(client.getMessager().getMessageHandler().sendCloseConnection());
    }
}
