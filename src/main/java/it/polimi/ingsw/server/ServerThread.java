package it.polimi.ingsw.server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket socket;
    private int idGame;
    private String nameUser;


    public ServerThread(Socket socket){
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s;
            while((s = input.readLine()) != null){
                if(s.length() <= 0) break;
                JSONObject obj;
                //try{
                //String s = input.

                obj  = (JSONObject) new JSONParser().parse(s);
                //}catch(Exception e){
                //    break;
                //}

                String command = obj.get("command").toString();

                if (command.equals("new_game")) {
                    MessageServer ms = new MessageStartGameServer(this);
                    ms.accept(new JSONServerVisitor(), obj);
                }

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
