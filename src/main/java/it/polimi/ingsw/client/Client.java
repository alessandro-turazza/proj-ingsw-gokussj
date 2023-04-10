package it.polimi.ingsw.client;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread implements Runnable{
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
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

                String s;
                JSONObject obj = new JSONObject();
                obj.put("command","new_game");
                obj.put("name",name);
                obj.put("numPlayers",  numPlayers);


                out.println(obj.toJSONString());

                String resp = input.readLine();
                JSONObject r = (JSONObject) new JSONParser().parse(resp);

                System.out.println(r.get("response"));



            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                Socket socket = new Socket(ipServer,PORT);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);


                JSONObject obj = new JSONObject();
                obj.put("command","enter_in_game");
                obj.put("name",name);
                obj.put("idGame",  idGame);

                out.println(obj.toJSONString());

                String s = input.readLine();

                JSONObject r = (JSONObject) new JSONParser().parse(s);

                System.out.println(r.get("response"));

                s = input.readLine();

                System.out.println(s);


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        //while (true);
    }
}
