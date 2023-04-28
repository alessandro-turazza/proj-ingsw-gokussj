package it.polimi.ingsw.client;

import it.polimi.ingsw.client.model.ClientModel;
import it.polimi.ingsw.client.view.ViewController;

import java.io.IOException;

public class Client {
    private ClientMessager messager;
    private ViewController viewController;
    private ClientModel model;

    public Client() throws IOException {
        this.messager = new ClientMessager(this);
        this.viewController = new ViewController(this);
        this.model = new ClientModel();
    }
    public ViewController getViewController() {
        return viewController;
    }

    public ClientMessager getMessager() {
        return messager;
    }

    public ClientModel getModel() {
        return model;
    }

    public void startClient(){
        this.viewController.startViewController();
        this.viewController.setClientDatas();
        this.messager.start();
    }

    /*private final int PORT = 4500;
    private final String ipServer= "localhost";
    private BufferedReader input;
    private PrintWriter out;
    private String name;
    private int idGame;
    private ClientMessageHandler messageHandler;
    private ViewController viewController;

    public Client(){}

    public void setUsername(String name) {
        this.name = name;
    }
    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }
    public ClientMessageHandler getMessageHandler() {
        return messageHandler;
    }
    public ViewController getViewController() {
        return viewController;
    }

    public void startClient() throws IOException {


        this.messageHandler = new ClientMessageHandler(this);
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

            //viewController.start();
            //this.wait();
            this.sendMessage(viewController.setClientDatas());

            do{
                messageIn = input.readLine();
                if(messageIn!=null) {
                    JSONObject obj = (JSONObject) new JSONParser().parse(messageIn);
                    mc = messageHandler.handleMessage(obj);
                    mc.accept(new JSONClientVisitor());
                }
            }while(true);

        } catch (Exception e) {
            throw new RuntimeException();
            //viewController.getView().showErrorMessage("Errore, server non connesso");
        }

    }*/


}

