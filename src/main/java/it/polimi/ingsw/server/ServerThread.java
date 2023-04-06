package it.polimi.ingsw.server;

import it.polimi.ingsw.user.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            try {
                JSONObject obj = (JSONObject) new JSONParser().parse(input.readLine());
                User user;
                user = new User(obj.get("name").toString());
                System.out.println("Connection established with " + user.getName());

                if(obj.get("command").toString().equals("new_game")){


                    ServerGamesData.createNewGame(user, Integer.parseInt(obj.get("numPlayers").toString()));

                    System.out.println("User " + user.getName() + " has just created a " + Integer.parseInt(obj.get("numPlayers").toString()) + " players game");

                }else if(obj.get("command").toString().equals("enter_in_game")){

                    boolean entered = ServerGamesData.addPlayerToGame(user, Integer.parseInt(obj.get("idGame").toString()));

                    if(entered){
                        System.out.println("User " + user.getName() + " has just joined to " + Integer.parseInt(obj.get("idGame").toString()) + " players game");

                        if(ServerGamesData.fullGame(Integer.parseInt(obj.get("idGame").toString())))
                            System.out.println("The game "+ Integer.parseInt(obj.get("idGame").toString()) + " can start (numPlayers reached)");
                    }else
                        System.out.println("Impossible to join the match");
                }

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            //while(true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
