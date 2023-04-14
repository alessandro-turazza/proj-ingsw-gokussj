package it.polimi.ingsw.server;

import it.polimi.ingsw.server.chat.ServerChatAccepter;
import it.polimi.ingsw.user.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket socket;
    private int idGame;
    private ServerSender ss;
    private User user;
    private boolean startGame;


    public ServerSender getSs() {
        return ss;
    }


    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        this.ss = new ServerSender(socket);
        this.startGame = false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStartGame(){
        this.startGame = true;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    @Override
    public void run() {
        try {

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s;

            ServerChatAccepter chat = new ServerChatAccepter();
            chat.acceptConnection(idGame);

            while(true){

                s = input.readLine();
                JSONObject obj;
                String command;
                MessageServer ms;
                if(s != null) {
                    obj = (JSONObject) new JSONParser().parse(s);
                    command = (String) obj.get("command");


                    if (command.equals("new_game")) {

                        ms = new MessageStartGameServer(chat.getServerChatWriter(), this, obj);
                        ms.accept(new JSONServerVisitor());

                    }else if(command.equals("enter_in_game")){

                        ms = new MessageEnterInGame(this, obj);
                        ms.accept(new JSONServerVisitor());
                        if(startGame)
                            Server.getServerGameFromId(idGame).updateStateGame();
                    }}
                        while(true){
                            s=input.readLine();
                            obj = (JSONObject) new JSONParser().parse(s);
                            command = (String) obj.get("command");
                            if(command.equals("drag_and_drop")){
                                ms = new MessageDragAndDropServer(this, obj);
                                ms.accept(new JSONServerVisitor());
                            }
                        }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getIdGame() {
        return idGame;
    }
}
