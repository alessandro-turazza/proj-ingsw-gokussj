package it.polimi.ingsw.client.client_prove;

import it.polimi.ingsw.client.Client;

public class ClientCreator1 {
    public static void main(String[] args){
        Client c1 = new Client("User1", true);
        c1.setNumPlayers(3);
        c1.start();
    }
}
