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

    private ClientSender sender;

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
            try {
                Socket socket = new Socket(ipServer,PORT);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                sender =new ClientSender(socket);
                if(creator)
                    sender.sendCreateGame(numPlayers,name);
                else sender.sendJoinGame(idGame, name);

                String resp = input.readLine();
                JSONObject obj = (JSONObject) new JSONParser().parse(resp);

                String response = (String) obj.get("response");

                MessageClient ms;
                if(response.equals("OK")){
                    ms= new MessageOKClient();                  //dovrà semplicemente confermare che ha il permesso di procedere (in sostanza non farà quasi nulla)
                    ms.accept(new JSONClientVisitor(), obj);
                }else if (response.equals("KO")){
                    ms = new MessageKOClient();                 //dovrà interrompere il processo e mandare un messaggio d'errore (e al più killare il thread e farne partire uno nuovo)
                    ms.accept(new JSONClientVisitor(), obj);
                }
                while (true){
                    resp = input.readLine();
                    obj = (JSONObject) new JSONParser().parse(resp);
                    String message = (String) obj.get("response");

                    if(message.equals("new_turn")){
                        ms = new MessageNewTurnClient();
                        ms.accept(new JSONClientVisitor(), obj);
                        if(((MessageNewTurnClient)ms).getUsername().equals(name)){
                            do {
                                //attende i comandi della view, che con un metodo caricheranno la drag e la drop sul Client Sender
                                sender.sendDragAndDrop();

                                resp = input.readLine();
                                obj = (JSONObject) new JSONParser().parse(resp);
                                message = (String) obj.get("response");

                            }while(response.equals("OK"));
                        }
                    }else{throw new RuntimeException();}
                }

               // System.out.println(r.get("response"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        //while (true);
    }

