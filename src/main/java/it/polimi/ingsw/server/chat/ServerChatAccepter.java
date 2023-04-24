package it.polimi.ingsw.server.chat;

public class ServerChatAccepter {
    /*private static final int READ_PORT = 4502;
    private static final int WRITE_PORT = 4501;
    private static ServerSocket serverSocketReader;
    private static ServerSocket serverSocketWriter;

    private ServerChatReader serverChatReader;

    private ServerChatWriter serverChatWriter;

    public ServerChatAccepter() throws Exception{
        try {
            serverSocketReader=new ServerSocket(READ_PORT);
            serverSocketWriter=new ServerSocket(WRITE_PORT);
        }catch (IOException e){throw new Exception();}
    }

    public void acceptConnection(int idGame) throws Exception{
        try{
        Socket sreadrer = serverSocketReader.accept();
        serverChatReader = new ServerChatReader(sreadrer, idGame);
        Socket swriter = serverSocketWriter.accept();
        serverChatWriter = new ServerChatWriter(swriter, idGame);
        serverChatReader.start();
        }catch (IOException e){throw new Exception();}
    }

    public ServerChatWriter getServerChatWriter() {
        return serverChatWriter;
    }*/
}
