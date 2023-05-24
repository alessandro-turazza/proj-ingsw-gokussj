package it.polimi.ingsw.server.visitor;

import it.polimi.ingsw.server.*;
import it.polimi.ingsw.server.message.MessageChatServer;
import it.polimi.ingsw.server.message.MessageCloseConnection;
import it.polimi.ingsw.server.message.MessageDragAndDropServer;
import it.polimi.ingsw.server.message.MessageEnterInGame;
import it.polimi.ingsw.server.message.MessageStartGameServer;
import it.polimi.ingsw.server.model.game_manager.GameManager;
import it.polimi.ingsw.server.model.user.User;

/*This class contains all the visit for each type of the messages from the client to the server in Visitor pattern*/

public class JSONServerVisitor implements VisitorServer{

    /*Visit for the creation of a game*/
   @Override
   public void visit(MessageStartGameServer m) {
       int idGame = m.getServerThread().getServer().insertNewGame(m.getServerThread(), m.getUser(), m.getNumPlayer());
       m.getServerThread().setIdGame(idGame);
       m.getServerThread().setUser(m.getUser());
       initializeVerifier(m.getServerThread());
       m.getServerThread().sendMessage(m.getServerThread().getController().sendOkConnection(idGame+""));
   }

    /*Visit for the joining into a game*/
    @Override
    public void visit(MessageEnterInGame m) {

       Server server = m.getServerThread().getServer();
       ServerGame sg = server.getServerGameFromId(m.getIdGame());


        if(sg != null){
            boolean res = sg.addNewPlayer(m.getServerThread(), m.getUser());

            if(res){
                m.getServerThread().setIdGame(m.getIdGame());
                initializeVerifier(m.getServerThread());
                m.getServerThread().sendMessage(m.getServerThread().getController().sendOkConnection(m.getIdGame()+""));
                m.getServerThread().setUser(m.getUser());
                m.getServerThread().getVeriferSender().setUser(m.getUser());
                if(sg.getPlayers().size() == sg.getGameManager().getNumUser()){
                    //sg.updateStateGame();
                    for(ServerThread st: sg.getPlayers()){
                        st.sendMessage(st.getController().sendStateGame(sg.getGameManager()));
                        st.getVeriferSender().setGameStarted(true);
                        System.out.println(st.getUser().getName() + "Settato verifier true");
                    }

                }

            }else
                m.getServerThread().sendMessage(m.getServerThread().getController().sendKoConnection("USER/FULL"));
        }else
            m.getServerThread().sendMessage(m.getServerThread().getController().sendKoConnection("NOTEX"));

    }

    /*Visit for the drag and drop*/
    @Override
    public  void visit(MessageDragAndDropServer m){
        ServerGame serverGame=m.getServerThread().getServer().getServerGameFromId(m.getServerThread().getIdGame());
        GameManager gm=serverGame.getGameManager();

        try{
            //Chiama UpdateGame passando la drag e la drop generate dai suoi attributi.
            User user=gm.updateGame(m.getDr().getRows(),m.getDr().getColumns(), m.getDr().getColumn());

            //m.getServerThread().getSs().sendOk();
           // m.getServerThread().sendMessage(m.getServerThread().getController().sendOkDED());

            if(user==null){
                //serverGame.endGame();
                for(ServerThread st: serverGame.getPlayers()){
                    st.sendMessage(st.getController().sendEndOfGame(gm));
                }

            }
            else {
                //serverGame.updateStateGame();//prepara il ServerThread per inviare i dati aggiornati
                for(ServerThread st: serverGame.getPlayers()){
                    System.out.println("MANDO NEW TURN");
                    st.sendMessage(st.getController().sendStateGame(gm));
                }

            }
        }
        catch (Exception e){
            //gestisce anche il caso di eccezioni(nel qual caso manda una KO al Client)
            //m.getServerThread().getSs().sendKO();
            e.printStackTrace();
            m.getServerThread().sendMessage(m.getServerThread().getController().sendKoDED());
        }


    }

    /*Visit for the chat messages*/
    @Override
    public void visit(MessageChatServer m) {
        ServerGame serverGame=m.getServerThread().getServer().getServerGameFromId(m.getServerThread().getIdGame());
        for(ServerThread st: serverGame.getPlayers()){
            st.sendMessage(st.getController().sendChatMessage(m.getObj()));
        }
    }

    /*Visit for the closing of the connection from client to server*/
    @Override
    public void visit(MessageCloseConnection m){
       m.getServerThread().setCloseConnection(true);
    }

    private void initializeVerifier(ServerThread st){
       VerifierBuffer buffer = new VerifierBuffer();
       VeriferSender sender = new VeriferSender();
       VerifierReciver reciver = new VerifierReciver();
       sender.setBuffer(buffer);
       reciver.setBuffer(buffer);
       sender.setGame(st.getServer().getServerGameFromId(st.getIdGame()));
       sender.setUser(st.getUser());
       sender.setWriteSocket(Server.getVerifierWriteSocket());
       reciver.setReadSocket(Server.getVerifierReadSocket());
       st.setVeriferSender(sender);
       st.setVerifierReciver(reciver);
       sender.setServerThread(st);
       sender.setGameStarted(false);
       sender.start();
       reciver.start();
    }
}
