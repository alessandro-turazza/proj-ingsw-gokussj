package it.polimi.ingsw.client.client_prove;

import it.polimi.ingsw.client.Client;

public class ClientCreator2 {
    public static void main(String[] args){
        Client c2 = new Client("User2", true);
        c2.setNumPlayers(2);
        c2.start();
    }
}
