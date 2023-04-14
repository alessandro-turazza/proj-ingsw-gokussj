package it.polimi.ingsw.server.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerChatAccepter {
    private static final int READ_PORT = 4502;
    private static final int WRITE_PORT = 4501;
    private static ServerSocket serverSocketReader;
    private static ServerSocket serverSocketWriter;

    public ServerChatAccepter() throws Exception{
        try {
            serverSocketReader=new ServerSocket(READ_PORT);
            serverSocketWriter=new ServerSocket(WRITE_PORT);
        }catch (IOException e){throw new Exception();}
    }

    public void acceptConnection(int idGame) throws Exception{
        try{
        Socket sreadrer = serverSocketReader.accept();
        ServerChatReader sr = new ServerChatReader(sreadrer, idGame);
        Socket swriter = serverSocketWriter.accept();
        ServerChatWriter sw = new ServerChatWriter(swriter, idGame);
        sr.start();
        sw.start();
        }catch (IOException e){throw new Exception();}
    }
}
