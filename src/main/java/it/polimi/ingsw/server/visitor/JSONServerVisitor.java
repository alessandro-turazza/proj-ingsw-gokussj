package it.polimi.ingsw.server.visitor;

import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.server.ServerGame;
import it.polimi.ingsw.server.ServerThread;
import it.polimi.ingsw.server.chat.ServerChatAccepter;
import it.polimi.ingsw.server.message.MessageDragAndDropServer;
import it.polimi.ingsw.server.message.MessageEnterInGame;
import it.polimi.ingsw.server.message.MessageStartGameServer;
import it.polimi.ingsw.server.model.game_manager.GameManager;
import it.polimi.ingsw.server.model.user.User;


public class JSONServerVisitor implements VisitorServer{

   @Override
   public void visit(MessageStartGameServer m) {
       int idGame = m.getServerThread().getServer().insertNewGame(m.getServerThread(), m.getUser(), m.getNumPlayer());
       m.getServerThread().setIdGame(idGame);
       m.getServerThread().setUser(m.getUser());
       m.getServerThread().sendMessage(m.getServerThread().getController().sendOkConnection(idGame+""));
       try {
           ServerChatAccepter chatAccepter = ServerChatAccepter.getAccepter();
           chatAccepter.acceptConnection(idGame);

       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }

    @Override
    public void visit(MessageEnterInGame m) {

       Server server = m.getServerThread().getServer();
       ServerGame sg = server.getServerGameFromId(m.getIdGame());


        if(sg != null){
            boolean res = sg.addNewPlayer(m.getServerThread(), m.getUser());

            if(res){
                m.getServerThread().setIdGame(m.getIdGame());
                m.getServerThread().sendMessage(m.getServerThread().getController().sendOkConnection(m.getIdGame()+""));
                m.getServerThread().setUser(m.getUser());

                if(sg.getPlayers().size() == sg.getGameManager().getNumUser()){
                    //sg.updateStateGame();
                    for(ServerThread st: sg.getPlayers())
                        st.sendMessage(st.getController().sendStateGame(sg.getGameManager()));
                }

            }else
                m.getServerThread().sendMessage(m.getServerThread().getController().sendKoConnection("USER/FULL"));
        }else
            m.getServerThread().sendMessage(m.getServerThread().getController().sendKoConnection("NOTEX"));

        try {
            ServerChatAccepter.getAccepter().acceptConnection(m.getIdGame());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public  void visit(MessageDragAndDropServer m){
        ServerGame serverGame=m.getServerThread().getServer().getServerGameFromId(m.getServerThread().getIdGame());
        GameManager gm=serverGame.getGameManager();

        try{
            //Chiama UpdateGame passando la drag e la drop generate dai suoi attributi.
            User user=gm.getTurnManager().updateGame(m.getDr().getRows(),m.getDr().getColumns(), m.getDr().getColumn());

            //m.getServerThread().getSs().sendOk();
            m.getServerThread().sendMessage(m.getServerThread().getController().sendOkDED());

            if(user==null){
                gm.endGame(); //gestisce ovviamente anche il caso di return=null
                //serverGame.endGame();
                for(ServerThread st: serverGame.getPlayers()){
                    st.sendMessage(st.getController().sendEndOfGame(gm));
                    st.setCloseConnection(true);
                }

            }
            else {
                //serverGame.updateStateGame();//prepara il ServerThread per inviare i dati aggiornati
                for(ServerThread st: serverGame.getPlayers()){
                    st.sendMessage(st.getController().sendStateGame(gm));
                }

            }
        }
        catch (Exception e){
            //gestisce anche il caso di eccezioni(nel qual caso manda una KO al Client)
            //m.getServerThread().getSs().sendKO();
            m.getServerThread().sendMessage(m.getServerThread().getController().sendKoDED());
        }


    }

}
