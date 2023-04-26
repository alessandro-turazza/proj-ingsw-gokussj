package it.polimi.ingsw.client;

import it.polimi.ingsw.client.message.MessageClient;
import it.polimi.ingsw.client.view.ViewController;
import it.polimi.ingsw.client.visitor.JSONClientVisitor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread implements Runnable{
    private final int PORT = 4500;
    private final String ipServer= "localhost";
    private BufferedReader input;
    private PrintWriter out;
    private String name;
    private int idGame;
    private ClientController controller;
    private ViewController viewController;

    public Client(){}

    public void setUsername(String name) {
        this.name = name;
    }
    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }
    public ClientController getController() {
        return controller;
    }
    public ViewController getViewController() {
        return viewController;
    }

    public void startClient() throws IOException {


        this.controller = new ClientController(this);
        this.viewController = new ViewController(this);

        viewController.startViewController();
        this.startConnection();
    }

    public void sendMessage(JSONObject obj){
        out.println(obj.toJSONString());
    }

    public void startConnection() {
        try {
            Socket socket = new Socket(ipServer, PORT);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String messageIn;
            String messageOut;
            MessageClient mc;

            this.sendMessage(viewController.setClientDatas());

            do{
                messageIn = input.readLine();
                if(messageIn!=null) {
                    JSONObject obj = (JSONObject) new JSONParser().parse(messageIn);
                    mc = controller.handleMessage(obj);
                    mc.accept(new JSONClientVisitor());
                }
            }while(true);

        } catch (Exception e) {
            viewController.getView().showErrorMessage("Errore, server non connesso");
        }

    }


}

