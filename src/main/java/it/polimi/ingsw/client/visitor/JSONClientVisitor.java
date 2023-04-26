package it.polimi.ingsw.client.visitor;

import it.polimi.ingsw.client.message.*;
import it.polimi.ingsw.client.view.Colors;

public class JSONClientVisitor implements VisitorClient {


    @Override
    public void visit(MessageGameStateClient element) {

    }

    @Override
    public void visit(MessageNewTurnClient element) {

    }

    @Override
    public void visit(MessageOKConnectionClient element) {
        //DA FARE CON VIEW (in element ci deve essere un client)
        System.out.println(Colors.GREEN + "Sei stato aggiunto correttamente alla partita " + element.getIdGame() + Colors.COLOR_RESET);
        System.out.println("In attesa degli altri giocatori...");
    }

    @Override
    public void visit(MessageKOConnectionClient element) {
        if(element.getObject().equals("NOTEX"))
            System.out.println(Colors.RED + "Errore, partita inesistente" + Colors.COLOR_RESET);
        else if(element.getObject().equals("USER/FULL"))
            System.out.println(Colors.RED + "Errore, username già in uso / partita piena" + Colors.COLOR_RESET);
        element.getClient().setUserDatas();
    }
    @Override
    public void visit(MessageEndGameClient element) {

    }
}
