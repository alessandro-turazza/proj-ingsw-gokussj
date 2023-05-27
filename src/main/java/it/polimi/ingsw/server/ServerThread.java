package it.polimi.ingsw.server;

import it.polimi.ingsw.server.message.MessageServer;
import it.polimi.ingsw.server.visitor.JSONServerVisitor;
import it.polimi.ingsw.server.model.user.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class implements the real connection between the server and the client: it receives and sends messages from and to the client
 * */

public class ServerThread extends Thread{

    private VeriferSender veriferSender;
    private VerifierReciver verifierReciver;
    private Socket socket;
    private Server server;
    private PrintWriter out;
    private BufferedReader input;
    private int idGame;
    private ServerController controller;
    private User user;

    private boolean closeConnection;


    public ServerThread(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.controller = new ServerController(this);
        this.closeConnection = false;
    }

    public Server getServer() {
        return server;
    }
    public ServerController getController() {
        return controller;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getIdGame() {
        return idGame;
    }
    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public void setCloseConnection(boolean closeConnection) {
        this.closeConnection = closeConnection;
        veriferSender.setCloseConnection(closeConnection);
        verifierReciver.setCloseConnection(closeConnection);
    }
    /**
     * This method runs in a different thread from the server and receives all the messages from the client
     * */
    @Override
    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String messageIn;
            MessageServer ms;


            do{
                messageIn = input.readLine();
                if(messageIn!=null) {
                    JSONObject obj = (JSONObject) new JSONParser().parse(messageIn);
                    ms = controller.handleMessage(obj);
                    ms.accept(new JSONServerVisitor());
                }
            }while(!closeConnection);
            System.out.println("Connessione chiusa con "+user.getName());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(JSONObject obj){
        out.println(obj.toJSONString());
    }

    public void setVeriferSender(VeriferSender veriferSender) {
        this.veriferSender = veriferSender;
    }

    public void setVerifierReciver(VerifierReciver verifierReciver) {
        this.verifierReciver = verifierReciver;
    }

    public VeriferSender getVeriferSender() {
        return veriferSender;
    }

}
