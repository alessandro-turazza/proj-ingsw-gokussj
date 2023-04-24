package it.polimi.ingsw.server.visitor;

import it.polimi.ingsw.server.model.game_manager.GameManager;
import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.server.ServerGame;
import it.polimi.ingsw.server.message.MessageDragAndDropServer;
import it.polimi.ingsw.server.message.MessageEnterInGame;
import it.polimi.ingsw.server.message.MessageStartGameServer;
import it.polimi.ingsw.server.model.user.User;


public class JSONServerVisitor implements VisitorServer{

   /* @Override
    public void visit(MessageStartGameServer m) {
        int idGame=Server.insertNewGame(m.getServerChatWriter(),m.getServerThread(), m.getUser(), m.getNumPlayer());
        m.getServerThread().setIdGame(idGame);
        m.getServerThread().getSs().sendOk();
        m.getServerThread().setUser(m.getUser());
    }*/
   @Override
   public void visit(MessageStartGameServer m) {
       int idGame= Server.insertNewGame(m.getServerThread(), m.getUser(), m.getNumPlayer());
       m.getServerThread().setIdGame(idGame);
       m.getServerThread().sendMessage(m.getServerThread().getController().sendOkConnection());
       m.getServerThread().setUser(m.getUser());
   }

    @Override
    public void visit(MessageEnterInGame m) {
        boolean res = Server.getServerGameFromId(m.getIdGame()).addNewPlayer(m.getServerThread(), m.getUser());

        if(res){
            m.getServerThread().setIdGame(m.getIdGame());
            m.getServerThread().sendMessage(m.getServerThread().getController().sendOkConnection());
            m.getServerThread().setUser(m.getUser());

            if(Server.getServerGameFromId(m.getIdGame()).getPlayers().size() == Server.getServerGameFromId(m.getIdGame()).getGameManager().getNumUser())
                Server.getServerGameFromId(m.getIdGame()).updateStateGame();
        }else
            m.getServerThread().sendMessage(m.getServerThread().getController().sendKoConnection());
    }

    @Override
    public  void visit(MessageDragAndDropServer m){
        ServerGame serverGame=Server.getServerGameFromId(m.getServerThread().getIdGame());
        GameManager gm=serverGame.getGameManager();
        User user=null;
        User activeUser=gm.getTurnManager().getUsers().activeUser();

        try{
            //Chiama UpdateGame passando la drag e la drop generate dai suoi attributi.
            user=gm.getTurnManager().updateGame(m.getX_coordinate(),m.getY_coordinate(),m.getColumn());
        }
        catch (Exception e){
            //gestisce anche il caso di eccezioni(nel qual caso manda una KO al Client)
            //m.getServerThread().getSs().sendKO();
            m.getServerThread().sendMessage(m.getServerThread().getController().sendKoDED());
        }
        //m.getServerThread().getSs().sendOk();
        m.getServerThread().sendMessage(m.getServerThread().getController().sendOkDED());

        if(user==null){
            gm.endGame(); //gestisce ovviamente anche il caso di return=null
            serverGame.endGame();
        }
        else serverGame.updateStateGame();//prepara il ServerThread per inviare i dati aggiornati
    }

}
