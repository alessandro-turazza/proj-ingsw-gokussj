package it.polimi.ingsw.server.chat;

import it.polimi.ingsw.server.JSONServerVisitor;
import it.polimi.ingsw.server.MessageChatServer;
import it.polimi.ingsw.server.MessageServer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerChatReader extends Thread{

    private int idGame;
    private Socket socket;
    public ServerChatReader(Socket socket, int idGame){
        this.socket=socket;
        this.idGame=idGame;
    }

    @Override
    public void run() throws RuntimeException {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s;
            while(true){
                s = input.readLine();
                JSONObject obj;
                String command;
                MessageServer ms;
                if(s != null) {
                    obj = (JSONObject) new JSONParser().parse(s);
                    ms= new MessageChatServer(this, obj);
                    ms.accept(new JSONServerVisitor());
                }
            }
        }catch(Exception e){throw new RuntimeException();}

    }

    public int getIdGame() {
        return idGame;
    }
}
