package it.polimi.ingsw.client.visitor;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.message.*;
import it.polimi.ingsw.server.model.user.User;

import java.util.ArrayList;

public class JSONClientVisitor implements VisitorClient {


    @Override
    public void visit(MessageGameStateClient element) {

    }

    @Override
    public void visit(MessageNewTurnClient element) {
        Client client = element.getClient();
        ArrayList<User> users = element.getStateGame().getUsersClone();

        client.getViewController().getView().showNormalMessage("Giocatori:");

        for(User u: users){
            if(u.getName().equals(element.getStateGame().getActiveUser()))
                client.getViewController().getView().showNormalMessage("->"+u.getName()+"<-");
            else
                client.getViewController().getView().showNormalMessage(u.getName());
        }

        element.getStateGame().getPlankClone().printPlank();

        client.getViewController().getView().showNormalMessage("Obiettivo comune 1: " + element.getStateGame().getCommonGoalsClone().get(0).getIdRule());

        client.getViewController().getView().showNormalMessage("Obiettivo comune 2: " + element.getStateGame().getCommonGoalsClone().get(1).getIdRule());



    }

    @Override
    public void visit(MessageOKConnectionClient element) {
        Client client = element.getClient();
        client.getViewController().getView().showCorrectMessage("Sei stato aggiunto correttamente alla partita " + element.getIdGame());
        client.getViewController().getView().showNormalMessage("In attesa degli altri giocatori...");
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
    public void visit(MessageEndGameClient element) {

    }
}
