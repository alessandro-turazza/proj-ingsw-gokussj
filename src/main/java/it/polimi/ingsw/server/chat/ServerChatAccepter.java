package it.polimi.ingsw.server.chat;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerChatAccepter {

    private static ServerChatAccepter instance;
    private static final int READ_PORT = 4502;
    private static final int WRITE_PORT = 4501;
    private static ServerSocket serverSocketReader;
    private static ServerSocket serverSocketWriter;
    private ServerChatReader serverChatReader;
    private static ArrayList<ServerChatWriter> serverChatWriters;


    private ServerChatAccepter() throws IOException{

            serverSocketReader=new ServerSocket(READ_PORT);
            serverSocketWriter=new ServerSocket(WRITE_PORT);
            serverChatWriters = new ArrayList<>();

    }

    public static ServerChatAccepter getAccepter() throws IOException {
        if (instance==null)
            instance = new ServerChatAccepter();
        return instance;
    }

    public void acceptConnection(int idGame) throws Exception{
        try{
        Socket sreadrer = serverSocketReader.accept();
        serverChatReader = new ServerChatReader(sreadrer, idGame);
        Socket swriter = serverSocketWriter.accept();
        ServerChatWriter scw = new ServerChatWriter(swriter, idGame);
        serverChatWriters.add(scw);
        serverChatReader.start();
        }catch (IOException e){throw new Exception();}
    }

    public static void sendAll(JSONObject obj, int idGame) throws Exception{
        for (ServerChatWriter scw : serverChatWriters)
            if(scw.getIdGame()==idGame)
                try {
                    scw.sendMessage(obj);
                }catch (IOException e){throw new Exception();}


    }
}
