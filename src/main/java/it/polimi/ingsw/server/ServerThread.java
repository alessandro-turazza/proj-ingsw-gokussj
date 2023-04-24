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

public class ServerThread extends Thread{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader input;
    private int idGame;
    private ServerController controller;
    private User user;



    public ServerController getController() {
        return controller;
    }


    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        this.controller = new ServerController(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    @Override
    public void run() {
        try {

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String messageIn;
            MessageServer ms;

            do{
                messageIn = input.readLine();
                JSONObject obj = (JSONObject) new JSONParser().parse(messageIn);
                ms = controller.handleMessage(obj);
                ms.accept(new JSONServerVisitor());
            }while(true);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(JSONObject obj){
        out.println(obj.toJSONString());
    }

    public int getIdGame() {
        return idGame;
    }
}
