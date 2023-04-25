package it.polimi.ingsw.client;

import it.polimi.ingsw.client.message.MessageClient;
import it.polimi.ingsw.client.view.CLI;
import it.polimi.ingsw.client.view.View;
import it.polimi.ingsw.client.visitor.JSONClientVisitor;
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

    private View view;
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
        System.out.println("Press C to start CLI interface");
        System.out.println("Press G to start GUI interface");

        Scanner in = new Scanner(System.in);

        char choose = Character.toUpperCase(in.nextLine().charAt(0));

        switch(choose){
            case 'C':
                view = new CLI();
                break;
            case 'G':
                //view = new GUI();
                break;
        }

        this.setUserDatas();

    }

    public void setUserDatas(){
        JSONObject userDatas;
        userDatas = view.lobby();

        this.name = userDatas.get("username").toString();

        if(userDatas.get("type").toString().equals("create"))
            this.creator = true;
        else
            this.creator = false;

        if(creator)
            this.numPlayers = Integer.parseInt(userDatas.get("numPlayers").toString());
        else
            this.idGame = Integer.parseInt(userDatas.get("idGame").toString());

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
            view.showErrorServer();
            //throw new RuntimeException(e);
        }

    }

    public void sendMessage(JSONObject obj){
        out.println(obj.toJSONString());
    }

}

