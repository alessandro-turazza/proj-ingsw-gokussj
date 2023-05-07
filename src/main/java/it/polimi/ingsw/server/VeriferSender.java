package it.polimi.ingsw.server;

import it.polimi.ingsw.server.message.MessageCloseConnection;
import it.polimi.ingsw.server.model.user.User;
import it.polimi.ingsw.server.visitor.JSONServerVisitor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class VeriferSender extends Thread{

    private boolean gameStarted;
    private ServerThread serverThread;
    private User user;
    private ServerGame game;
    private VerifierBuffer buffer;
    private ServerSocket writeSocket;
    private Socket socket;
    private boolean closeConnection = false;

    @Override
    public void run() {
        try {
            socket = writeSocket.accept();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            do{
                out.println("CHECK_CONNECTION");
                sleep(5000);
               if(buffer.getMessage().equals("CONNECTED")){
                   buffer.clear();
               }

                else {
                   for (ServerThread st : game.getPlayers()) {
                       st.sendMessage(sendDisconnection());
                       if(isGameStarted())
                           st.sendMessage(st.getController().sendEndOfGame(game.getGameManager()));
                   }
                   new MessageCloseConnection(serverThread).accept(new JSONServerVisitor());
                }
            }while(!closeConnection);
        }catch (IOException e){System.out.println("Problema IO");}
        catch (InterruptedException e){System.out.println("Problema Thread");}


    }

    public void setBuffer(VerifierBuffer buffer) {
        this.buffer = buffer;
    }


    public JSONObject sendDisconnection(){
        JSONObject obj = new JSONObject();
        obj.put("response", "DISCONNECTION");
        obj.put("user", user.getName());
        return obj;
    }
    public void setCloseConnection(boolean closeConnection) {
        this.closeConnection = closeConnection;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGame(ServerGame game) {
        this.game = game;
    }

    public void setWriteSocket(ServerSocket writeSocket) {
        this.writeSocket = writeSocket;
    }
    public void setServerThread(ServerThread serverThread) {
        this.serverThread = serverThread;
    }

    public synchronized void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }
    public synchronized boolean isGameStarted() {
        return gameStarted;
    }
}
