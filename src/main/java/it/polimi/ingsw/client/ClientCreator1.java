package it.polimi.ingsw.client;

public class ClientCreator1 {
    public static void main(String[] args){
        Client c1 = new Client("User1", true);
        c1.setNumPlayers(3);
        c1.start();
    }
}
