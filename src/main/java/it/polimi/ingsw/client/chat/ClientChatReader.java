package it.polimi.ingsw.client.chat;

public class ClientChatReader extends Thread{
    /*private final int PORT = 4501;
    private final String ipServer= "localhost";
    @Override
    public void run() throws RuntimeException {

        try {
            Socket socket = new Socket(ipServer, PORT);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true)
            {
               String resp = input.readLine();
               JSONObject obj = (JSONObject) new JSONParser().parse(resp);
               MessageChatClient ms=new MessageChatClient(this, obj);
               ms.accept(new JSONClientVisitor());
            }

        }catch (Exception e){throw new RuntimeException();}

    }*/
}
