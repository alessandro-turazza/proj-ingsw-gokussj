package it.polimi.ingsw.client.chat;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientChatReader extends Thread{
    private final int PORT = 50001;
    private final String ipServer= "31.27.157.205";
    private Chat chat;
    @Override
    public void run() throws RuntimeException {

        try {
            Socket socket = new Socket(ipServer, PORT);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true)
            {
               String resp = input.readLine();
               JSONObject obj = (JSONObject) new JSONParser().parse(resp);
               chat.chatAdd(obj);
            }
        }catch (Exception e){throw new RuntimeException();}

    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
