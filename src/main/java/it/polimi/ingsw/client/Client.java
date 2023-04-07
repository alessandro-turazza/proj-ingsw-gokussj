package it.polimi.ingsw.client;

import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client extends Thread{
    private final int PORT = 4500;
    private final String ipServer= "localhost";
    private String name;
    private int idGame;

    private int numPlayers;
    private boolean creator;

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

    @Override
    public void run() {
        if(creator){
            try {
                Socket socket = new Socket(ipServer,PORT);
                //PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter( socket.getOutputStream()));

                JSONObject obj = new JSONObject();
                obj.put("command","new_game");
                obj.put("name",name);
                obj.put("numPlayers",  numPlayers);

                out.write(obj.toJSONString());
                out.flush();
                //out.close();
                //socket.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                Socket socket = new Socket(ipServer,PORT);
                //PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter( socket.getOutputStream()));

                JSONObject obj = new JSONObject();
                obj.put("command","enter_in_game");
                obj.put("name",name);
                obj.put("idGame",  idGame);

                out.write(obj.toJSONString());
                out.flush();
                out.close();

                //out.print(obj);
                socket.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //while (true);
    }
}
