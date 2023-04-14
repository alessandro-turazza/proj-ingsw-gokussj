package it.polimi.ingsw.server;

import it.polimi.ingsw.game_manager.GameManager;
import it.polimi.ingsw.user.User;


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
       int idGame=Server.insertNewGame(m.getServerThread(), m.getUser(), m.getNumPlayer());
       m.getServerThread().setIdGame(idGame);
       m.getServerThread().getSs().sendOk();
       m.getServerThread().setUser(m.getUser());
   }

    @Override
    public void visit(MessageEnterInGame m) {
        boolean res = Server.getServerGameFromId(m.getIdGame()).addNewPlayer(m.getServerThread(), m.getUser());

        if(res){
            m.getServerThread().setIdGame(m.getIdGame());
            m.getServerThread().getSs().sendOk();
            m.getServerThread().setUser(m.getUser());

            if(Server.getServerGameFromId(m.getIdGame()).getPlayers().size() == Server.getServerGameFromId(m.getIdGame()).getGameManager().getNumUser())
                Server.getServerGameFromId(m.getIdGame()).updateStateGame();
        }else
            m.getServerThread().getSs().sendKO();
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
            m.getServerThread().getSs().sendKO();
        }
        m.getServerThread().getSs().sendOk();
        if(user==null){
            gm.endGame(); //gestisce ovviamente anche il caso di return=null
            serverGame.endGame();
        }
        else serverGame.updateStateGame();//prepara il ServerThread per inviare i dati aggiornati
    }

    @Override
    public void visit(MessageChatServer m) {
        Server.getServerGameFromId(m.getReader().getIdGame()).messageChat(m.getPlayerName(), m.getMessage());
    }
}
