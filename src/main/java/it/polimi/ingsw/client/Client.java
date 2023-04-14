package it.polimi.ingsw.client;

import it.polimi.ingsw.client.chat.ClientChatReader;
import it.polimi.ingsw.client.chat.ClientChatWriter;
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

    private ClientSender sender;

    private ClientChatReader chatReader;
    private ClientChatWriter chatWriter;

    public Client(String name, boolean creator){
        this.name = name;
        this.creator = creator;
        chatReader=new ClientChatReader();
        chatWriter=new ClientChatWriter();
        chatWriter.setPlayerName(this.name);

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
            try {
                    Socket socket = new Socket(ipServer, PORT);
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    String resp;
                    chatWriter.start();
                    chatReader.start();
                    sender = new ClientSender(out);
                    JSONObject obj;
                    String response;
                    do {
                        if (creator)
                            sender.sendCreateGame(numPlayers, name);
                        else sender.sendJoinGame(idGame, name);

                        resp = input.readLine();
                        obj = (JSONObject) new JSONParser().parse(resp);
                        response = obj.get("response").toString();
                        if(response.equals("KO"))
                            throw new Exception();
                    }while(!response.equals("OK"));

                MessageClient ms;
                while (true){
                    resp = input.readLine();
                    obj = (JSONObject) new JSONParser().parse(resp);
                    response = (String) obj.get("response");

                    if(response.equals("new_turn")) {
                        ms = new MessageNewTurnClient(this, obj);
                        ms.accept(new JSONClientVisitor());
                        if (((MessageNewTurnClient) ms).getStateGame().getActiveUser().equals(name)) {
                            do {
                                //attende i comandi della view, che con un metodo caricheranno la drag e la drop sul Client Sender
                                sender.sendDragAndDrop();

                                resp = input.readLine();
                                obj = (JSONObject) new JSONParser().parse(resp);
                                response = (String) obj.get("response");

                            } while (!response.equals("OK"));
                        }
                    }else if(response.equals("end_game")){
                        ms= new MessageEndGameClient();
                        ms.accept(new JSONClientVisitor());
                    }
                }
                //Killa i thread della chat
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }

