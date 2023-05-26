package it.polimi.ingsw.client;

import it.polimi.ingsw.client.message.MessageClient;
import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.client.visitor.JSONClientVisitor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMessager extends Thread{ //manage the input and the output stream of message from and to the server
    private final int PORT = 50000;
    //private final String ipServer= "31.27.157.205";
    private final String ipServer= "localhost";

    //private final String ipServer= "192.168.1.16";
    //private final String ipServer="172.17.0.2";
    private BufferedReader input;
    private PrintWriter out;
    private Client client;
    private ClientMessageHandler messageHandler;

    public ClientMessager(Client client) {
        this.client = client;
        this.messageHandler = new ClientMessageHandler(client);
        Socket socket;

        try{
            socket = new Socket(ipServer, PORT);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }catch(Exception e){
            System.out.println(Colors.RED + "Server OFF" + Colors.COLOR_RESET);
            System.out.println("Sembra che il server non sia disponibile, ritenta più tardi...");
            System.exit(0);
        }

    }

    public ClientMessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void sendMessage(JSONObject obj){ //called to send a message to the server
        out.println(obj.toJSONString());
    }
    @Override
    public void run() { //remains active to recive message form the server
        try {

            String messageIn;
            MessageClient mc;

            do{
                messageIn = input.readLine();

                if(messageIn!=null) {
                    JSONObject obj = (JSONObject) new JSONParser().parse(messageIn);
                    mc = messageHandler.handleMessage(obj);
                    mc.accept(new JSONClientVisitor());
                }

            }while(true);

        } catch (Exception e) {
            client.getViewController().showErrorMessage("Connessione a internet assente.\nControlla la tua connessione e poi riavvia l'applicazione.");
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public String getIpServer() {
        return ipServer;
    }
}
