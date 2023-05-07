package it.polimi.ingsw.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class VerifierReciver extends Thread{
    private VerifierBuffer buffer;
    private static final int PORT = 50002;

    private ServerSocket readSocket;
    private Socket socket;
    private boolean closeConnection = false;

    @Override
    public void run() {
        try {
            socket = readSocket.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
                do{
                    message=input.readLine();
                    if (message!=null)
                        buffer.setMessage(message);
                }while(!closeConnection);
        }catch(Exception e)
    {
        System.out.println("");

    }

    }

    public void setBuffer(VerifierBuffer buffer) {
        this.buffer = buffer;
    }

    public void setCloseConnection(boolean closeConnection) {
        this.closeConnection = closeConnection;
    }

    public void setReadSocket(ServerSocket readSocket) {
        this.readSocket = readSocket;
    }
}
