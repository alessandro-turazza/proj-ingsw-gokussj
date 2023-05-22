package it.polimi.ingsw.client;

import it.polimi.ingsw.client.message.MessageClient;
import it.polimi.ingsw.client.view.Colors;
import it.polimi.ingsw.client.visitor.JSONClientVisitor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMessager extends Thread{
    private final int PORT = 50000;
    //private final String ipServer= "31.27.157.205";
    private final String ipServer= "localhost";

    //private final String ipServer= "192.168.1.16";

    private BufferedReader input;
    private PrintWriter out;
    private Client client;
    private ClientMessageHandler messageHandler;

    public ClientMessager(Client client) throws IOException {
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
        }

    }

    public ClientMessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void sendMessage(JSONObject obj){
        out.println(obj.toJSONString());
    }
    @Override
    public void run() {
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
            throw new RuntimeException();
            //viewController.getView().showErrorMessage("Errore, server non connesso");
        }

    }

    public String getIpServer() {
        return ipServer;
    }
}
