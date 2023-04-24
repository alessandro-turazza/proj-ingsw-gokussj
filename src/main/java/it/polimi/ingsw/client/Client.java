package it.polimi.ingsw.client;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread implements Runnable{
    private final int PORT = 4500;
    private final String ipServer= "localhost";

    private BufferedReader input;

    private PrintWriter out;
    private String name;
    private int idGame;

    private int numPlayers;
    private boolean creator;

    private ClientController controller;

    public Client(){}
    public Client(String name, boolean creator){
        this.name = name;
        this.creator = creator;
    }
    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public void setNumPlayers(int numPlayers) {
        if(numPlayers >= 2 && numPlayers <= 4)
            this.numPlayers = numPlayers;
    }

    public void startClient(){
        System.out.println("Insert a username");
        Scanner in = new Scanner(System.in);

        this.name = in.nextLine();

        System.out.println("Press C to create a new game");
        System.out.println("Press J to join into an existent game");

        char choose = Character.toUpperCase(in.nextLine().charAt(0));

        while(choose != 'C' && choose != 'J'){
            System.out.println("Invalid character, retype your chosen");
            choose = Character.toUpperCase(in.nextLine().charAt(0));
        }

        switch (choose){
            case 'C':
                System.out.println("Insert the number of the players in game");
                this.numPlayers = in.nextInt();
                this.creator = true;
                break;
            case 'J':
                System.out.println("Insert the ID of a game");
                this.idGame = in.nextInt();
                this.creator = false;
                break;
        }

        this.startConnection();

    }
    public void startConnection() {
        try {
            Socket socket = new Socket(ipServer, PORT);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String messageIn;
            String messageOut;
            MessageClient mc;


            controller = new ClientController(this);

            if (creator)
                this.sendMessage(controller.sendCreateGame(numPlayers, name));
            else
                this.sendMessage(controller.sendJoinGame(idGame, name));

            do{
                messageIn = input.readLine();
                JSONObject obj = (JSONObject) new JSONParser().parse(messageIn);
                mc = controller.handleMessage(obj);
                mc.accept(new JSONClientVisitor());
            }while(true);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void sendMessage(JSONObject obj){
        out.println(obj.toJSONString());
    }

}

