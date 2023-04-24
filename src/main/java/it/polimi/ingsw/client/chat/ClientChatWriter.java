package it.polimi.ingsw.client.chat;

public class ClientChatWriter extends Thread{

    /*private final int PORT = 4502;
    private final String ipServer= "localhost";

    private String playerName;

    private String message;
    @Override
    public void run() throws RuntimeException{

        try{
            Socket socket = new Socket(ipServer, PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            ClientController cs= new ClientController(out);
            while (true)
            {
                //si mette in wait e attende il comando da view, che setterà il testo del messaggio
                cs.sendMessage(playerName, message);
            }
        }catch (Exception e){throw new RuntimeException();}

    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setMessage(String message) {
        this.message = message;
    }*/
}
